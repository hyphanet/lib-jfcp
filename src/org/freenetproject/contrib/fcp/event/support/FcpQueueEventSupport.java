/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.event.support;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.freenetproject.contrib.fcp.event.AllDataEvent;
import org.freenetproject.contrib.fcp.event.DataFoundEvent;
import org.freenetproject.contrib.fcp.event.FcpKeyRequestedEvent;
import org.freenetproject.contrib.fcp.event.FcpSimpleProgressEvent;
import org.freenetproject.contrib.fcp.event.GetFailedEvent;
import org.freenetproject.contrib.fcp.event.support.AbstractFcpEventSupport.NotifyHelper;
import org.freenetproject.contrib.fcp.listener.FcpQueueListener;
import org.freenetproject.contrib.fcp.message.client.ClientGet;
import org.freenetproject.contrib.fcp.message.node.AllData;
import org.freenetproject.contrib.fcp.message.node.DataFound;
import org.freenetproject.contrib.fcp.message.node.GetFailed;
import org.freenetproject.contrib.fcp.message.node.SimpleProgress;

/**
 *
 * @author res
 */
public class FcpQueueEventSupport extends AbstractFcpEventSupport<FcpQueueListener>{
    private static Logger logger = Logger.getLogger(FcpQueueEventSupport.class.getName());
    
    private Map<String, ClientGet> _items = Collections.synchronizedMap(new HashMap());
    
    
    NotifyHelper _keyRequestedNotifier = new NotifyHelper<FcpKeyRequestedEvent>() {
        void notifyListener(FcpQueueListener l, FcpKeyRequestedEvent e) {l.keyRequested(e);}
    };   
    
    NotifyHelper _simpleProgressNotifier = new NotifyHelper<FcpSimpleProgressEvent>() {
        void notifyListener(FcpQueueListener l, FcpSimpleProgressEvent e) {l.simpleProgressUpdate(e);}
    }; 
    
    NotifyHelper _dataFoundNotifier = new NotifyHelper<DataFoundEvent>() {
        void notifyListener(FcpQueueListener l, DataFoundEvent e) {l.dataFound(e);}
    };
    
    NotifyHelper _allDataNotifier = new NotifyHelper<AllDataEvent>() {
        void notifyListener(FcpQueueListener l, AllDataEvent e) {l.allData(e);}
    };
    
    NotifyHelper _getFailedNotifier = new NotifyHelper<GetFailedEvent>() {
        void notifyListener(FcpQueueListener l, GetFailedEvent e) {l.getFailed(e);}
    };
    
    
    public void fireKeyRequested(ClientGet cg){
        if(_items.get(cg.getId()) != null)
            logger.info("duplicate request id: " + cg.getId());
        _items.put(cg.getId(), cg);
        _keyRequestedNotifier.notifyListeners(new FcpKeyRequestedEvent(cg));
    }
    
    public void fireSimpleProgressUpdate(SimpleProgress sp){
        _simpleProgressNotifier.notifyListeners(new FcpSimpleProgressEvent(sp));
    }
    
    public void fireDataFound(DataFound df){
        _dataFoundNotifier.notifyListeners(new DataFoundEvent(df));
    }
    
    public void fireAllData(AllData ad){
        _allDataNotifier.notifyListeners(new AllDataEvent(ad));
    }
    
    public void fireGetFailed(GetFailed gf){
        _getFailedNotifier.notifyListeners(new GetFailedEvent(gf));
    }
}
