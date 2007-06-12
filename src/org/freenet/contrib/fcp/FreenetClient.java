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
import org.freenet.contrib.fcp.message.client.GenerateSSK;
import org.freenet.contrib.fcp.message.client.ListPeers;

/**
 *
 * @author res
 */
public class FreenetClient{
    private static String DEFAULT_CLIENT_NAME = "FreenetClient";
    private FcpEventSupportRepository _eventSupport;
    private FcpEventSource _eventSource;
    private FcpConnection _conn;
    
    public FreenetClient(){
        this(new NodeAddress(), DEFAULT_CLIENT_NAME);
    }
    
    public FreenetClient(String name){
        this(new NodeAddress(), name);
    }
    
    /** Creates a new instance of FreenetClient */
    public FreenetClient(NodeAddress na, String name) {
        _eventSupport = new FcpEventSupportRepository();
        _eventSource = new FcpEventSource(_eventSupport);
        _conn = new FcpConnection(na, _eventSupport, name);
    }
    
    public FcpEventSource getEventSource(){
        return _eventSource;
    }

    
    public FcpConnection getConnection() {
        return _conn;
    }

    public void refreshPeerList(){
        _conn.sendMessage(new ListPeers(true, true));
    }
    
    public void get(String uri, String id){
        ClientGet message = new ClientGet(uri, id);
        message.setVerbosity(1);
        message.setPriority(2);
        _conn.sendMessage(message);
    }
    
    public void generateSSK(String id){
        _conn.sendMessage(new GenerateSSK(id));
    }
}
