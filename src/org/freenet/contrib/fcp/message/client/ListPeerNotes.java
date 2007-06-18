/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.client;

import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *This message lists the peer notes for a given peer of your Freenet node.
 *
 * @author Ralph Smithen
 */
public class ListPeerNotes extends ClientMessage{
    
    /** Creates a new instance of ListPeers */
    public ListPeerNotes() {
    }
    
    /** Creates a new instance of ListPeers */
    public ListPeerNotes(String nodeId) {
        setNodeId(nodeId);
    }


    public void setNodeId(String nodeId) {
        _fields.put("NodeIdentifier", nodeId);
    }

    public String getNodeId() {
        return _fields.get("NodeIdentifier");
    }

    public void fireEvents(FcpEventSupportRepository eventSupport) {
    }
 
}
