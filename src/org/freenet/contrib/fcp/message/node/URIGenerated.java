/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *This indicates the final URI of the data inserted; this may not be the same as the original URI, 
 * which could have been CHK@ ("insert this as a CHK and tell me what its CHK is"), 
 * or an Signed Subspace Key (SSK) using a private key.
 *
 * @author Ralph Smithen
 */
public class URIGenerated extends NodeMessage{
    private String _uri;
    private byte[] _data;
    
    /**
     * Creates a new instance of URIGenerated
     */
    public URIGenerated() {
    }
    
    /**
     * @inheritDoc 
     */
    protected void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getSSKKeypairEventSupport().fireURIGenerated(this);
    }
    
    public String getId() {
        return _fields.get("Identifier");
    }
    
    public void setId(String id) {
        _fields.put("Identifier", id);
    }
    
    public String getUri() {
        return _fields.get("URI");
    }

    public void setUri(String uri) {
        _fields.put("URI", uri);
    }
 }
