/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.event.support;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author res
 */
class AbstractFcpEventSupport<ListenerType> {
    Set<ListenerType> _listeners = Collections.synchronizedSet(new HashSet());
    
    public void addListener(ListenerType l){
        if(l != null)
            _listeners.add(l);
    }
    
    public void removeListener(ListenerType l){
        if(l != null)
            _listeners.remove(l);
    }
    
    protected abstract class NotifyHelper<EventType>{
        void notifyListeners(EventType e){
            synchronized(_listeners){
                for(ListenerType l : (Set<ListenerType>) _listeners){
                    notifyListener(l, e);
                }
            }
        }
        abstract void notifyListener(ListenerType l, EventType e);
    }
    
}
