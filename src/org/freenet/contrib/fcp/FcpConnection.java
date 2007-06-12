/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;
import org.freenet.contrib.fcp.message.MessageBuilderException;
import org.freenet.contrib.fcp.message.client.ClientHello;
import org.freenet.contrib.fcp.message.client.ClientMessage;
import org.freenet.contrib.fcp.message.node.NodeMessage;
import org.freenet.contrib.fcp.message.node.NodeMessageBuilder;



/**
 * Responsible for sending data to the node.
 * @author res
 */
public class FcpConnection {
    private static Logger logger = Logger.getLogger(FcpConnection.class.getName());
    
    private String _fcpVersion = "2.0";
    private String _clientName = "FreenetClient";
    
    private Socket _socket;
    private PrintStream _out;
    private BufferedReader _in;
    private NodeAddress _nodeAddress;
    private ExecutorService _messageSender;
    private boolean _socketOpen;
    private MessageReaderThread _messageReaderThread;
    private FcpEventSupportRepository _eventSupport;
    
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
        logger.fine("opening socket to " + _nodeAddress.getHostName() + ":" + _nodeAddress.getPort());
        _socket = new Socket(_nodeAddress.getHostName(), _nodeAddress.getPort());
        _in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        _out = new PrintStream(_socket.getOutputStream());
        _messageSender = Executors.newSingleThreadExecutor();
        _messageReaderThread = new MessageReaderThread();
        _messageReaderThread.start();
        _socketOpen = true;
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
        if(_in != null){
            try {
                _in.close();
            } catch (Exception ex) {
                logger.warning("error closing input stream: " + ex.getMessage());
            }
            _in = null;
        }
        if(_out != null){
            try {
                _out.close();
            } catch (Exception ex) {
                logger.warning("error closing output stream: " + ex.getMessage());
            }
            _out = null;
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
//            logger.fine("sending message : " + _message.getHeaderString());
            _message.writeMessage(_out);
            if(_out.checkError()){
                logger.warning("error sending message:\n\n" + _message.getMessageString());
                close();
            }else{
                _message.fireEvents(_eventSupport);
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
        this._nodeAddress = nodeAddress;
    }
    
    
    private class MessageReaderThread extends Thread{
        private boolean _running;
        
        public void run(){
            _running = true;
            String line;
            try {
                
                while(_socketOpen){
                    NodeMessageBuilder builder = new NodeMessageBuilder();
                    try {
                        builder.parse(_in);
                        NodeMessage message = builder.build();
                        message.fireEvents(_eventSupport);
                    } catch (MessageBuilderException ex) {
                        if(_socketOpen)
                            logger.warning("error building node message: " + ex.getMessage());
                    }
                    
                }
                
            } catch (IOException ex) {
                logger.warning("error reading stream: " + ex.getMessage());
            }
            
            _running = false;
            if(_socketOpen)
                close();
        }
        
        public boolean isRunning() {
            return _running;
        }
        
    }
    
}
