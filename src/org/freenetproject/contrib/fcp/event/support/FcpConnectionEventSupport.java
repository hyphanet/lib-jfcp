/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.event.support;

import org.freenetproject.contrib.fcp.event.FcpConnectEvent;
import org.freenetproject.contrib.fcp.event.FcpDisconnectEvent;
import org.freenetproject.contrib.fcp.event.support.AbstractFcpEventSupport.NotifyHelper;
import org.freenetproject.contrib.fcp.listener.FcpConnectionListener;
import org.freenetproject.contrib.fcp.message.node.NodeHello;

/**
 *
 * @author res
 */
public class FcpConnectionEventSupport extends AbstractFcpEventSupport<FcpConnectionListener>{
    NotifyHelper _connectNotifier = new NotifyHelper<FcpConnectEvent>() {
        void notifyListener(FcpConnectionListener l, FcpConnectEvent e) {l.nodeConnected(e);}
    };
    
    NotifyHelper _disconnectNotifier = new NotifyHelper<FcpDisconnectEvent>() {
        void notifyListener(FcpConnectionListener l, FcpDisconnectEvent e) {l.nodeDisconnected(e);}
    };
    
    
    public void fireFcpConnected(NodeHello nh){
        _connectNotifier.notifyListeners(new FcpConnectEvent(nh));
    }
    
    public void fireFcpDisconnected(){
        _disconnectNotifier.notifyListeners(new FcpDisconnectEvent());
    }
}
