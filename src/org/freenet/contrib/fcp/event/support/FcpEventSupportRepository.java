/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.event.support;

/**
 * Holds references to all the client's event sources.
 * @author Ralph Smithen
 */
public class FcpEventSupportRepository {
    
   private FcpConnectionEventSupport _connectionEventSupport = new FcpConnectionEventSupport();
   private FcpPeerListEventSupport _peerListEventSupport = new FcpPeerListEventSupport();
   private FcpQueueEventSupport _queueEventSupport = new FcpQueueEventSupport();
   private FcpSSKKeypairEventSupport _keypairEventSupport = new FcpSSKKeypairEventSupport();

    public FcpConnectionEventSupport getConnectionEventSupport() {
        return _connectionEventSupport;
    }

    public FcpPeerListEventSupport getPeerListEventSupport() {
        return _peerListEventSupport;
    }

    public FcpQueueEventSupport getQueueEventSupport() {
        return _queueEventSupport;
    }

    public FcpSSKKeypairEventSupport getSSKKeypairEventSupport() {
        return _keypairEventSupport;
    }
    
}
