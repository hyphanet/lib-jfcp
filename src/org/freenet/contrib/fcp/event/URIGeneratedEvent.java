/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.event;

import org.freenet.contrib.fcp.message.node.SSKKeypair;
import org.freenet.contrib.fcp.message.node.URIGenerated;

/**
 *
 * @author Ralph Smithen
 */
public class URIGeneratedEvent extends FcpEvent<URIGenerated>{
    
    /** Creates a new instance of URIGeneratedEvent */
    public URIGeneratedEvent(URIGenerated ug) {
        super(ug);
    }
    
}
