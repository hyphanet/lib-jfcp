/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.event.support;

import java.util.HashMap;
import java.util.Map;
import org.freenet.contrib.fcp.NodeInfo;
import org.freenet.contrib.fcp.event.FcpPeerListUpdatedEvent;
import org.freenet.contrib.fcp.event.support.AbstractFcpEventSupport.NotifyHelper;
import org.freenet.contrib.fcp.listener.FcpPeerListListener;
import org.freenet.contrib.fcp.message.node.Peer;

/**
 *
 * @author res
 */
public class FcpPeerListEventSupport extends AbstractFcpEventSupport<FcpPeerListListener>{
    private Map<String, Peer> _peers;
    
    NotifyHelper _peerListUpdatedNotifier = new NotifyHelper<FcpPeerListUpdatedEvent>() {
        void notifyListener(FcpPeerListListener l, FcpPeerListUpdatedEvent e) {l.peerListUpdated(e);}
    };

    
    public void firePeerListRequested(){
        _peers = new HashMap();
    }
    
    public void firePeerUpdated(Peer peer){
        _peers.put(peer.getId(), peer);
    }
    
    public void firePeerListUpdated(){
        _peerListUpdatedNotifier.notifyListeners(new FcpPeerListUpdatedEvent(_peers));
    }
}
