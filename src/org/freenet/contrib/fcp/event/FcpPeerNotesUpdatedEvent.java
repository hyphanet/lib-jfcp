/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.event;

import java.util.Vector;
import org.freenet.contrib.fcp.message.node.EndListPeerNotes;
import org.freenet.contrib.fcp.message.node.PeerNote;

/**
 *
 * @author Ralph Smithen
 */
public class FcpPeerNotesUpdatedEvent extends FcpEvent<EndListPeerNotes>{
    Vector<PeerNote> _peerNotes;
    /**
     * Creates a new instance of FcpPeerNotesUpdatedEvent
     */
    public FcpPeerNotesUpdatedEvent(EndListPeerNotes elpn, Vector<PeerNote> peerNotes) {
        super(elpn);
        _peerNotes = peerNotes;
    }
    
    public Vector<PeerNote> getPeerNotes(){
        return _peerNotes;
    }
    
}
