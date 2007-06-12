/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.event;

import java.util.Map;
import org.freenet.contrib.fcp.NodeInfo;
import org.freenet.contrib.fcp.message.client.ClientGet;

/**
 *
 * @author res
 */
public class FcpKeyRequestedEvent extends FcpEvent<ClientGet>{
    
    /**
     * Creates a new instance of FcpPeerListUpdatedEvent
     */
    public FcpKeyRequestedEvent(ClientGet cg) {
        super(cg);
    }
    
    
    
}
