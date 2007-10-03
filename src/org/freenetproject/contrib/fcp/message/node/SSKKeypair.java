/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.message.node;

import org.freenetproject.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 * This is sent from the Freenet node to a client program in response to the client 
 * issuing a {@link org.freenet.contrib.fcp.message.client.GenerateSSK GenerateSSK} command.
 *
 * @author Ralph Smithen
 */
public class SSKKeypair extends NodeMessage{
    
    /** Creates a new instance of SSKKeypair */
    public SSKKeypair() {
    }

    /**
     * 
     * @inheritDoc 
     */
    protected void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getSSKKeypairEventSupport().fireKeypairReceived(this);
    }

    public String getId() {
        return _fields.get("Identifier");
    }

    public void setId(String id) {
        _fields.put("Identifier", id);
    }
    
    public String getInsertURI() {
        return _fields.get("InsertURI");
    }

    public void setInsertURI(String uri) {
        _fields.put("InsertURI", uri);
    }
    
    public String getRequestURI() {
        return _fields.get("RequestURI");
    }

    public void setRequestURI(String uri) {
        _fields.put("RequestURI", uri);
    }
    
    
}
