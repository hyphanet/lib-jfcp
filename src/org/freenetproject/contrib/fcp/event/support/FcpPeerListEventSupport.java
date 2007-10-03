/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.event.support;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.freenetproject.contrib.fcp.NodeInfo;
import org.freenetproject.contrib.fcp.event.FcpPeerListUpdatedEvent;
import org.freenetproject.contrib.fcp.event.FcpPeerNotesUpdatedEvent;
import org.freenetproject.contrib.fcp.event.support.AbstractFcpEventSupport.NotifyHelper;
import org.freenetproject.contrib.fcp.listener.FcpPeerListListener;
import org.freenetproject.contrib.fcp.message.client.ListPeerNotes;
import org.freenetproject.contrib.fcp.message.node.EndListPeerNotes;
import org.freenetproject.contrib.fcp.message.node.Peer;
import org.freenetproject.contrib.fcp.message.node.PeerNote;

/**
 *
 * @author Ralph Smithen
 */
public class FcpPeerListEventSupport extends AbstractFcpEventSupport<FcpPeerListListener>{
    private Map<String, Peer> _peers;
    private Map<String, Vector<PeerNote>> _peerNotes = Collections.synchronizedMap(new HashMap());
    
    NotifyHelper _peerListUpdatedNotifier = new NotifyHelper<FcpPeerListUpdatedEvent>() {
        void notifyListener(FcpPeerListListener l, FcpPeerListUpdatedEvent e) {l.peerListUpdated(e);}
    };
    
    NotifyHelper _peerNotesUpdatedNotifier = new NotifyHelper<FcpPeerNotesUpdatedEvent>() {
        void notifyListener(FcpPeerListListener l, FcpPeerNotesUpdatedEvent e) {l.peerNotesUpdated(e);}
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
    
    public void firePeerNote(PeerNote pn){
        synchronized(_peerNotes){
            _peerNotes.get(pn.getNodeId()).add(pn);
        }
    }
    
    public void firePeerNotesRequested(ListPeerNotes lpn){
        _peerNotes.put(lpn.getNodeId(), new Vector());
    }
    
    public void firePeerNotesUpdated(EndListPeerNotes elpn){
         _peerNotesUpdatedNotifier.notifyListeners(
                 new FcpPeerNotesUpdatedEvent(elpn, _peerNotes.get(elpn.getNodeId())));
    }
    
    
}
