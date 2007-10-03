/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.message.node;

import org.freenetproject.contrib.fcp.event.support.FcpEventSupportRepository;
import org.freenetproject.contrib.fcp.message.UploadFrom;

/**
 *This is the node replying to a {@link org.freenet.contrib.fcp.message.client.ClientPut ClientPut} 
 * and confirming the details or the request 
 * (possibly for clients other than the one that issued the ClientPut, if it is on the global queue)
 *
 * @author Ralph Smithen
 */
public class PersistentPut extends NodeMessage{
    
    /**
     * @inheritDoc 
     */
    protected void fireEvents(FcpEventSupportRepository eventSupport) {
        
    }

    public String getId() {
        return _fields.get("Identifier");
    }
    
    public String getUri() {
        return _fields.get("URI");
    }   
    
    public int getVerbosity() {
        return Integer.parseInt(_fields.get("Verbosity"));
    }
    
    public UploadFrom getUploadFrom() {
        return UploadFrom.valueOf(_fields.get("UploadFrom"));
    }

    public String getFileName() {
        return _fields.get("FileName");
    }
    
    public String getTargetFilename() {
        return _fields.get("TargetFilename");
    }
    
    public String getContentType() {
        return _fields.get("Metadata.ContentType");
    }
    
    public boolean isGlobal() {
        return Boolean.parseBoolean(_fields.get("Global"));
    }
    
    public int getDataLength() {
        return Integer.parseInt(_fields.get("DataLength"));
    }
    
    public int getMaxRetries() {
        return Integer.parseInt(_fields.get("MaxRetries"));
    }
    
}
