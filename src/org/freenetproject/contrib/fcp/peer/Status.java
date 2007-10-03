/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.peer;

/**
 * Connection status of peer.
 * @author Ralph Smithen
 */
public enum Status {
        CONNECTED,
        DISCONNECTING,
        DISCONNECTED,
        BACKED_OFF,
        TOO_NEW,
        TOO_OLD,
        NEVER_CONNECTED
}
