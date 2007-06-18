/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;
import org.freenet.contrib.fcp.message.FcpMessageInputStream;
import org.freenet.contrib.fcp.message.FcpMessageOutputStream;
import org.freenet.contrib.fcp.message.MessageBuilderException;
import org.freenet.contrib.fcp.message.client.ClientHello;
import org.freenet.contrib.fcp.message.client.ClientMessage;
import org.freenet.contrib.fcp.message.node.NodeMessage;



/**
 * Responsible for sending data to the node.
 *
 * @author Ralph Smithen
 */
public class FcpConnection {
    private static Logger logger = Logger.getLogger(FcpConnection.class.getName());
    
    private String _fcpVersion = "2.0";
    private String _clientName = "FreenetClient";
    
    private Socket                      _socket;
    private FcpMessageOutputStream      _mos;
    private FcpMessageInputStream       _mis;
    private NodeAddress                 _nodeAddress;
    private ExecutorService             _messageSender;
    private MessageReaderThread         _messageReaderThread;
    private FcpEventSupportRepository   _eventSupport;
    private boolean                     _socketOpen;
    
    /** Creates a new instance of FcpConnection */
    FcpConnection(NodeAddress na, FcpEventSupportRepository eventSupport) {
        _nodeAddress = na;
        _eventSupport = eventSupport;
    }
    
    /** Creates a new instance of FcpConnection */
    FcpConnection(NodeAddress na, FcpEventSupportRepository eventSupport, String name) {
        this(na, eventSupport);
        _clientName = name;
    }
    
    /**
     * Opens a socket to the node.
     * @throws java.net.UnknownHostException if host not found.
     * @throws java.io.IOException on IO error.
     */
    public void open() throws UnknownHostException, IOException{
        if(_socketOpen)
            return;
        logger.fine("opening socket to " + _nodeAddress.getHostName() + ":" + _nodeAddress.getPort());
        _socket = new Socket(_nodeAddress.getHostName(), _nodeAddress.getPort());
        _mis = new FcpMessageInputStream(_socket.getInputStream());
        _mos = new FcpMessageOutputStream(_socket.getOutputStream());
        _messageSender = Executors.newSingleThreadExecutor();
        _messageReaderThread = new MessageReaderThread();
        _socketOpen = true;
        _messageReaderThread.start();
        sendMessage(new ClientHello(_fcpVersion, _clientName));
    }
    
    /**
     * Closes the connection to the node.
     */
    public void close(){
        _socketOpen = false;
        if(_messageSender != null){
            _messageSender.shutdownNow();
            _messageSender = null;
        }
        _messageReaderThread = null;
        if(_socket != null){
            try {
                _socket.close();
            } catch (Exception ex) {
                logger.warning("error closing socket: " + ex.getMessage());
            }
            _socket = null;
        }
        if(_mis != null){
            try {
                _mis.close();
            } catch (Exception ex) {
                logger.warning("error closing input stream: " + ex.getMessage());
            }
            _mis = null;
        }
        if(_mos != null){
            try {
                _mos.close();
            } catch (Exception ex) {
                logger.warning("error closing output stream: " + ex.getMessage());
            }
            _mos = null;
        }
        _eventSupport.getConnectionEventSupport().fireFcpDisconnected();
    }
    
    /**
     * Sends a message to the node.  This method returns immediately,
     * the message is put in a queue to be sent.
     * @param message The message to be sent.
     */
    public void sendMessage(ClientMessage message){
        if(_socketOpen)
            _messageSender.execute(new MessageHolder(message));
    }
    
    private class MessageHolder implements Runnable{
        private final ClientMessage _message;
        
        MessageHolder(ClientMessage message){
            _message = message;
        }
        
        public void run() {
            if(_mos == null)
                return;
            try {
                _mos.writeMessage(_message);
                _mos.flush();
                _message.fireEvents(_eventSupport);
            } catch (IOException ex) {
                logger.warning("IO error sending message: " + _message.getHeaderString());
                close();
            } catch (MessageBuilderException ex) {
                logger.warning("invalid message:\n\n" + _message.getMessageString() + "\n\n");
            }
        }
    }
    
    
    
    /**
     * Determine whether the connection is up.
     * @return boolean reflecting the connection's upness.
     */
    public boolean isSocketOpen() {
        return _socketOpen;
    }
    
    /**
     * get the node address for this connection
     * @return the {@link NodeAddress NodeAddress}
     */
    public NodeAddress getNodeAddress() {
        return _nodeAddress;
    }
    
    /**
     * Setter for NodeAddress.  Doesn't trigger a reconnection.
     * @param nodeAddress the new {@link NodeAddress NodeAddress}
     */
    public void setNodeAddress(NodeAddress nodeAddress) {
        _nodeAddress = nodeAddress;
    }
    
    
    private class MessageReaderThread extends Thread{        
        public void run(){
            try {
                while(_socketOpen){
                    try {
                        NodeMessage message = _mis.readMessage();
                        message.fireEvents(_eventSupport);
                    } catch (MessageBuilderException ex) {
                            logger.warning("error building node message: " + ex.getMessage());
                    }
                }
            } catch (IOException ex) {
                if(_socketOpen)
                    logger.warning("error reading stream: " + ex.getMessage());
            }
            if(_socketOpen)
                close();
        }
        
    }
    
}
