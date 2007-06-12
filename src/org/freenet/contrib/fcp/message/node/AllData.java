/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *
 * @author res
 */
public class AllData extends NodeMessage implements DataHoldingMessage{
    private String _uri;
    /** Creates a new instance of DataFound */
    public AllData() {
    }
    
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
    
    public String getData() {
        return _fields.get("Data");
    }
    
    public void setData(String data) {
        _fields.put("Data", data);
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
