/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.listener;

import org.freenetproject.contrib.fcp.event.FcpConnectEvent;
import org.freenetproject.contrib.fcp.event.FcpDisconnectEvent;

/**
 *
 * @author res
 */
public interface FcpConnectionListener {
    public void nodeConnected(FcpConnectEvent e);
    public void nodeDisconnected(FcpDisconnectEvent e);
}
