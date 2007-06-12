/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp;

import java.net.InetSocketAddress;

/**
 *
 * @author res
 */
public class NodeAddress extends InetSocketAddress{
    public static int DEFAULT_PORT = 9482;
    public static String DEFAULT_HOST = "127.0.0.1";
    
    /** Creates a new instance of NodeAddress */
    public NodeAddress() {
        super(DEFAULT_HOST, DEFAULT_PORT);
    }
    
    public NodeAddress(int port){
        super(DEFAULT_HOST, port);
    }
    
    public NodeAddress(String host, int port){
        super(host, port);
    }
    
}
