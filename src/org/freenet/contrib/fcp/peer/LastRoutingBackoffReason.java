/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.peer;

/**
 *
 * @author Ralph Smithen
 */
public enum LastRoutingBackoffReason {
        AcceptedTimeout,
        FatalTimeout,
        ForwardRejectedOverload,
        ForwardRejectedOverload2    
}
