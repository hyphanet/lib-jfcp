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
public class ModifyPeerNote extends ClientMessage{
    
    /** Creates a new instance of ModifyPeerNote */
    public ModifyPeerNote(String nodeId) {
        setNodeId(nodeId);
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

    public void fireEvents(FcpEventSupportRepository eventSupport) {
    }
 
}
