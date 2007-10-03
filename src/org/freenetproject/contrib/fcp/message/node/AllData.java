/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.message.node;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.freenetproject.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 * For a {@link org.freenet.contrib.fcp.message.client.ClientGet ClientGet} with 
 * {@link org.freenet.contrib.fcp.message.ReturnType ReturnType}=<code>direct</code>, 
 * the data is returned directly to the client, 
 * all at once, using the <code>AllData</code> message. Obviously in many situations this 
 * will not be desirable, hence the other <code>ReturnType</code> options. 
 * Persistent direct requests will not send this immediately on completion of the request; 
 * see {@link GetRequestStatus GetRequestStatus}.
 *
 * @author Ralph Smithen
 */
public class AllData extends NodeMessage{
    private String _uri;
    private byte[] _data;
    
    /**
     * Creates a new instance of AllData
     */
    public AllData() {
    }
    

    /**
     * @inheritDoc 
     */
    protected void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getQueueEventSupport().fireAllData(this);
    }
    
    public String getId() {
        return _fields.get("Identifier");
    }
    
    public void setId(String id) {
        _fields.put("Identifier", id);
    }

    public String getUri() {
        return _uri;
    }

    public void setUri(String uri) {
        _uri = uri;
    }
    
    public String toString(){
        try {
            return URLEncoder.encode(_uri, "utf-8");
        } catch (UnsupportedEncodingException ignore) { }
        return _uri;
    }
}
