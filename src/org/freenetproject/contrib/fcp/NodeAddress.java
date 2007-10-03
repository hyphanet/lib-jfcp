/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp;

import java.net.InetSocketAddress;

/**
 * Connection properties of friend.
 * @author Ralph Smithen
 */
public class NodeAddress extends InetSocketAddress{
    public static int DEFAULT_PORT = 9481;
    public static String DEFAULT_HOST = "127.0.0.1";
    
    /** Creates a new instance of NodeAddress. */
    public NodeAddress() {
        super(DEFAULT_HOST, DEFAULT_PORT);
    }
    
    /** Creates a new instance of NodeAddress specifying a non-standard port. */
    public NodeAddress(int port){
        super(DEFAULT_HOST, port);
    }
    
    /** Creates a new instance of NodeAddress specifying the host and port. */
    public NodeAddress(String host, int port){
        super(host, port);
    }
    
}
