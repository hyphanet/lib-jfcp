/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *
 * @author res
 */
public class DataFound extends NodeMessage{

    /** Creates a new instance of DataFound */
    public DataFound() {
    }
    
    public void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getQueueEventSupport().fireDataFound(this);
    }

    public String getId() {
        return _fields.get("Identifier");
    }

    public void setId(String id) {
        _fields.put("Identifier", id);
    }

    public String getContentType() {
        return _fields.get("Metadata.ContentType");
    }

    public void setContentType(String contentType) {
        _fields.put("Metadata.ContentType", contentType);
    }

    public int getDataLength() {
        return Integer.parseInt(_fields.get("DataLength"));
    }

    public void setDataLength(int dataLength) {
        _fields.put("ContentType", String.valueOf(dataLength));
    }
}
