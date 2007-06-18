/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.client;

import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 * This command allows an client program to remotely shut down a Freenet node. 
 * A confirmation message will be sent (ProtocolError code 18: Shutting down)
 * @author Ralph Smithen
 */
public class ShutDown extends ClientMessage{
    
    /** Creates a new instance of ShutDown */
    public ShutDown() {
    }

    public void fireEvents(FcpEventSupportRepository eventSupport) {
    }
    
}
