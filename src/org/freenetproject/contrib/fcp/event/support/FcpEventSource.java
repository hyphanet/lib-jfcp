/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.event.support;

import org.freenetproject.contrib.fcp.listener.FcpConnectionListener;
import org.freenetproject.contrib.fcp.listener.FcpPeerListListener;
import org.freenetproject.contrib.fcp.listener.FcpQueueListener;
import org.freenetproject.contrib.fcp.listener.FcpSSKKeypairListener;

/**
 * Serves to hide the event-firing functionality.
 * @author Ralph Smithen
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
    
    public void removeConnectionListener(FcpConnectionListener l){
        _repository.getConnectionEventSupport().removeListener(l);
    }
    
    public void addPeerListListener(FcpPeerListListener l){
        _repository.getPeerListEventSupport().addListener(l);
    }
    
    public void removePeerListListener(FcpPeerListListener l){
        _repository.getPeerListEventSupport().removeListener(l);
    }
    
    public void addQueueListener(FcpQueueListener l){
        _repository.getQueueEventSupport().addListener(l);
    }
    
    public void removeQueueListener(FcpQueueListener l){
        _repository.getQueueEventSupport().removeListener(l);
    }
    
    public void addKeypairListener(FcpSSKKeypairListener l){
        _repository.getSSKKeypairEventSupport().addListener(l);
    }
    
    public void removeKeypairListener(FcpSSKKeypairListener l){
        _repository.getSSKKeypairEventSupport().removeListener(l);
    }

}
