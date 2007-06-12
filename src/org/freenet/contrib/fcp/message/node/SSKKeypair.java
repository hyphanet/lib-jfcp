/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 * This is a message of the Freenet Client Protocol 2.0 (FCP 2.0).
 * It is sent from the Freenet node to a client program in response to the client 
 * issuing a GenerateSSK command.
 * @author res
 */
public class SSKKeypair extends NodeMessage{
    
    /** Creates a new instance of SSKKeypair */
    public SSKKeypair() {
    }

    /**
     * 
     * @inheritDoc 
     */
    public void fireEvents(FcpEventSupportRepository eventSupport) {
        
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
