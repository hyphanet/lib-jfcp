/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.client;

import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *
 * @author res
 */
public class GenerateSSK extends ClientMessage{
    
    public GenerateSSK(String id){
        setId(id);
    }
    
    public void fireEvents(FcpEventSupportRepository eventSupport) {
    }
    
    public String getId() {
        return _fields.get("Identifier");
    }
    
    public void setId(String id) {
        _fields.put("Identifier", id);
    }
}
