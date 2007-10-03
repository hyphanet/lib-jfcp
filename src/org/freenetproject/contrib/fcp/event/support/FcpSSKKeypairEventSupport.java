/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.event.support;

import java.util.logging.Logger;
import org.freenetproject.contrib.fcp.event.SSKKeypairEvent;
import org.freenetproject.contrib.fcp.event.URIGeneratedEvent;
import org.freenetproject.contrib.fcp.event.support.AbstractFcpEventSupport.NotifyHelper;
import org.freenetproject.contrib.fcp.listener.FcpSSKKeypairListener;
import org.freenetproject.contrib.fcp.message.node.SSKKeypair;
import org.freenetproject.contrib.fcp.message.node.URIGenerated;

/**
 *
 * @author Ralph Smithen
 */
public class FcpSSKKeypairEventSupport extends AbstractFcpEventSupport<FcpSSKKeypairListener>{
    private static Logger logger = Logger.getLogger(FcpSSKKeypairEventSupport.class.getName());
    
    NotifyHelper _keypairReceivedNotifier = new NotifyHelper<SSKKeypairEvent>() {
        void notifyListener(FcpSSKKeypairListener l, SSKKeypairEvent e) {l.keypairReceived(e);}
    };  
    
    NotifyHelper _uriGeneratedNotifier = new NotifyHelper<URIGeneratedEvent>() {
        void notifyListener(FcpSSKKeypairListener l, URIGeneratedEvent e) {l.uriGenerated(e);}
    };  
    
    
    
    public void fireKeypairReceived(SSKKeypair kp){
        _keypairReceivedNotifier.notifyListeners(new SSKKeypairEvent(kp));
    }
    
    public void fireURIGenerated(URIGenerated ug){
        _uriGeneratedNotifier.notifyListeners(new URIGeneratedEvent(ug));
    }
    
}
