/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.event.support;

import org.freenet.contrib.fcp.listener.FcpConnectionListener;
import org.freenet.contrib.fcp.listener.FcpPeerListListener;
import org.freenet.contrib.fcp.listener.FcpQueueListener;

/**
 *
 * @author res
 */
public class FcpEventSource {
    private FcpEventSupportRepository _repository;
    /** Creates a new instance of FcpEventSource */
    public FcpEventSource(FcpEventSupportRepository repository) {
        _repository = repository;
    }
    
    public void addConnectionListener(FcpConnectionListener l){
        _repository.getConnectionEventSupport().addListener(l);
    }
    
    public void removeFcpConnectionListener(FcpConnectionListener l){
        _repository.getConnectionEventSupport().removeListener(l);
    }
    
    public void addPeerListListener(FcpPeerListListener l){
        _repository.getPeerListEventSupport().addListener(l);
    }
    
    public void addQueueListener(FcpQueueListener l){
        _repository.getQueueEventSupport().addListener(l);
    }

}
