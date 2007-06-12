/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *
 * @author res
 */
public class EndListPeers extends NodeMessage{

    public void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getPeerListEventSupport().firePeerListUpdated();
    }

}
