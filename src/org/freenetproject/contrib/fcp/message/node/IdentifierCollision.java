/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.message.node;

import org.freenetproject.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 * This happens when a client tries to reuse an Identifier. Identifiers are unique to the specific request. 
 * They can be reused after that request has completed and removed from the queue.
 * @author Ralph Smithen
 */
public class IdentifierCollision extends NodeMessage{

    /**
     * @inheritDoc 
     */
    protected void fireEvents(FcpEventSupportRepository eventSupport) {
        // @todo: notify who?
    }

    public String getId() {
        return _fields.get("Identifier");
    }

    public void setId(String id) {
        _fields.put("Identifier", id);
    }
}
