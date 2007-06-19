/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.client;

import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *This message asks the Freenet node for a list of other Freenet nodes connected directly to you (peers).
 *
 * @author Ralph Smithen
 */
public class ListPeers extends ClientMessage{
    
    /** Creates a new instance of ListPeers */
    public ListPeers() {
    }
    
    /** Creates a new instance of ListPeers */
    public ListPeers(boolean withMetadata, boolean withVolatile) {
        setWithMetadata(withMetadata);
        setWithVolatile(withVolatile);
    }

    public boolean isWithMetadata() {
        return Boolean.parseBoolean(_fields.get("WithMetadata"));
    }

    public void setWithMetadata(boolean withMetadata) {
        _fields.put("WithMetadata", String.valueOf(withMetadata));
    }

    public boolean isWithVolatile() {
        return Boolean.parseBoolean(_fields.get("WithVolatile"));
    }

    public void setWithVolatile(boolean withVolatile) {
        _fields.put("WithVolatile", String.valueOf(withVolatile));
    }     
    
    protected void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getPeerListEventSupport().firePeerListRequested();
    }   
}
