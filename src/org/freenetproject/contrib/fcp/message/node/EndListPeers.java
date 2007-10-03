/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.message.node;

import org.freenetproject.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *This indicates the end of a list of {@link Peer Peer} messages that have been sent in response to a 
 * {@link org.freenet.contrib.fcp.message.client.ListPeers ListPeers} message.
 * @author Ralph Smithen
 */
public class EndListPeers extends NodeMessage{

    /**
     * @inheritDoc 
     */
    protected void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getPeerListEventSupport().firePeerListUpdated();
    }

}
