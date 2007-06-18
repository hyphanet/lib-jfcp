/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.event.support;

import java.util.logging.Logger;
import org.freenet.contrib.fcp.event.SSKKeypairEvent;
import org.freenet.contrib.fcp.event.support.AbstractFcpEventSupport.NotifyHelper;
import org.freenet.contrib.fcp.listener.FcpSSKKeypairListener;
import org.freenet.contrib.fcp.message.node.SSKKeypair;

/**
 *
 * @author Ralph Smithen
 */
public class FcpSSKKeypairEventSupport extends AbstractFcpEventSupport<FcpSSKKeypairListener>{
    private static Logger logger = Logger.getLogger(FcpSSKKeypairEventSupport.class.getName());
    
    NotifyHelper _keypairReceivedNotifier = new NotifyHelper<SSKKeypairEvent>() {
        void notifyListener(FcpSSKKeypairListener l, SSKKeypairEvent e) {l.keypairReceived(e);}
    };   
    
    
    
    public void fireKeypairReceived(SSKKeypair kp){
        _keypairReceivedNotifier.notifyListeners(new SSKKeypairEvent(kp));
    }
    
}
