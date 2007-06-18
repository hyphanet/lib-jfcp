/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

import org.freenet.contrib.fcp.NodeInfo;
import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *This indicates the end of a list of {@link PeerNote PeerNote} messages that have been sent
 * in response to a {@link org.freenet.contrib.fcp.message.client.ListPeerNotes ListPeerNotes} message.
 * @author Ralph Smithen
 */
public class EndListPeerNotes extends NodeMessage{
    
    public EndListPeerNotes(){
    }
    
    public void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getPeerListEventSupport().firePeerNotesUpdated(this);
    }
    
    public void setNodeId(String nodeId) {
        _fields.put("NodeIdentifier", nodeId);
    }
    
    public String getNodeId() {
        return _fields.get("NodeIdentifier");
    }
}
