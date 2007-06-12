/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.event;

import org.freenet.contrib.fcp.message.node.SSKKeypair;

/**
 *
 * @author res
 */
public class SSKKeypairEvent extends FcpEvent<SSKKeypair>{
    
    /** Creates a new instance of SSKKeypairEvent */
    public SSKKeypairEvent(SSKKeypair kp) {
        super(kp);
    }
    
}
