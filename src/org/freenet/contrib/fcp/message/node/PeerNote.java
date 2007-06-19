/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

import org.freenet.contrib.fcp.NodeInfo;
import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *This provides a peer note of a peer of the node.
 * @author Ralph Smithen
 */
public class PeerNote extends NodeMessage{
    
    public PeerNote(){
    }
    
    /**
     * @inheritDoc 
     */
    protected void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getPeerListEventSupport().firePeerNote(this);
    }

    public void setNodeId(String nodeId) {
        _fields.put("NodeIdentifier", nodeId);
    }

    public String getNodeId() {
        return _fields.get("NodeIdentifier");
    }
    
    public void setNoteType(int type){
        _fields.put("PeerNoteType", String.valueOf(type));
    }
    
    public int getNoteType(){
        return Integer.parseInt(_fields.get("PeerNoteType"));
    }
    
    public void setNoteText(String s) {
        _fields.put("NoteText", s);
    }

    public String getNoteText() {
        return _fields.get("NoteText");
    }
}
