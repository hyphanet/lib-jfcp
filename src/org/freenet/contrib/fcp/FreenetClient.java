/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp;

import java.io.IOException;
import java.net.UnknownHostException;
import org.freenet.contrib.fcp.event.support.FcpEventSource;
import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;
import org.freenet.contrib.fcp.message.client.ClientGet;
import org.freenet.contrib.fcp.message.client.ClientMessage;
import org.freenet.contrib.fcp.message.client.ClientPut;
import org.freenet.contrib.fcp.message.client.GenerateSSK;
import org.freenet.contrib.fcp.message.client.ListPeerNotes;
import org.freenet.contrib.fcp.message.client.ListPeers;

/**
 * The main class of the library, start here!
 * Note that the api may be subject to change.
 * @author Ralph Smithen
 */
public class FreenetClient{
    private static String DEFAULT_CLIENT_NAME = "FreenetClient";
    private FcpEventSupportRepository _eventSupport;
    private FcpEventSource _eventSource;
    private FcpConnection _conn;
    
    /**
     * Creates a new client called "FreenetClient" that will attempt to connect 
     * to 127.0.0.1:9481.
     */
    public FreenetClient(){
        this(new NodeAddress(), DEFAULT_CLIENT_NAME);
    }
    
    /**
     * Creates a named client that attempts to connect to 127.0.0.1:9481.
     * @param name the name of the client
     */
    public FreenetClient(String name){
        this(new NodeAddress(), name);
    }
    
    /**
     * Creates a new instance of FreenetClient
     * @param na where to connect
     * @param name how the client's identified by the node
     */
    public FreenetClient(NodeAddress na, String name) {
        _eventSupport = new FcpEventSupportRepository();
        _eventSource = new FcpEventSource(_eventSupport);
        _conn = new FcpConnection(na, _eventSupport, name);
    }
    
    /**
     * Returns an object by which interested classes can register to listen 
     * for incoming message events.
     * @return the event source
     */
    public FcpEventSource getEventSource(){
        return _eventSource;
    }

    
    /**
     * Get the client's connection to manipulate.
     * @return the conn
     */
    public FcpConnection getConnection() {
        return _conn;
    }

    /**
     * Request the peer data from node.
     */
    public void refreshPeerList(){
        _conn.sendMessage(new ListPeers(true, true));
    }
    
    /**
     * Request peer notes from node.
     */
    public void listPeerNotes(String peer){
        _conn.sendMessage(new ListPeerNotes(peer));
    }
    
    /**
     * Request data from node.
     * @param uri the key, e.g. CHK@blah
     * @param id an identifier for use in your app
     */
    public void get(String uri, String id){
        ClientGet cg = new ClientGet(uri, id);
        cg.setVerbosity(1);
        cg.setPriority(2);
        _conn.sendMessage(cg);
    }
    
    
    /**
     * Inserts data to node.
     * @param uri 
     * @param id 
     * @param data 
     */
    public void put(String uri, String id, byte[] data){
        ClientPut cp = new ClientPut(uri, id, data);
        cp.setVerbosity(1);
        cp.setPriority(2);
        _conn.sendMessage(cp);
    }
    
    /**
     * Will probably change
     * @param id an id for your convenience
     */
    public void generateSSK(String id){
        _conn.sendMessage(new GenerateSSK(id));
    }
}
