/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;
import org.freenet.contrib.fcp.message.DataHoldingMessage;

/**
 *For a ClientGet with ReturnType=direct, the data is returned directly to the client, 
 * all at once, using the AllData message. Obviously in many situations this will not be desirable, 
 * hence the other ReturnType options. Persistent direct requests will not send this 
 * immediately on completion of the request; see GetRequestStatus.
 *
 * @author Ralph Smithen
 */
public class AllData extends NodeMessage implements DataHoldingMessage{
    private String _uri;
    private byte[] _data;
    
    /**
     * Creates a new instance of AllData
     */
    public AllData() {
    }
    

    /**
     * 
     * @inheritDoc 
     */
    public void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getQueueEventSupport().fireAllData(this);
    }
    
    public String getId() {
        return _fields.get("Identifier");
    }
    
    public void setId(String id) {
        _fields.put("Identifier", id);
    }
    
    public int getDataLength() {
        return Integer.parseInt(_fields.get("DataLength"));
    }
    
    public void setDataLength(int dataLength) {
        _fields.put("DataLength", String.valueOf(dataLength));
    }
    
    public byte[] getData() {
        return _data;
    }
    
    public void setData(byte[] data) {
        _data = data;
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
