/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *Sent from the Freenet node in response to a 
 * {@link org.freenet.contrib.fcp.message.client.ClientHello ClientHello} message. The node tells us 
 * what version it is, what protocol version it's using, whether testnet mode is enabled, and how many 
 * compression codecs are currently supported (this is important with the StartedCompression message).
 *
 * @author Ralph Smithen
 */
public class NodeHello extends NodeMessage{

    /**
     * 
     * @inheritDoc 
     */
    public void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getConnectionEventSupport().fireFcpConnected(this);
    }
    
    public int getCompressionCodecs(){
        return Integer.parseInt(_fields.get("CompressionCodecs"));
    }
    
    public boolean isTestNet(){
        return Boolean.parseBoolean(_fields.get("TestNet"));
    }
    
    public String getNodeLanguage(){
        return _fields.get("NodeLanguage");
    }
    
    public String getExtRevision(){
        return _fields.get("ExtRevision");
    }
    
    public String getNode(){
        return _fields.get("Node");
    }
    
    public String getBuild(){
        return _fields.get("Build");
    }
    
    public String getFCPVersion(){
        return _fields.get("FCPVersion");
    }
    
    public String getConnectionIdentifier(){
        return _fields.get("ConnectionIdentifier");
    }
    
    public String getRevision(){
        return _fields.get("Revision");
    }
    
    public String getVersion(){
        return _fields.get("Version");
    }
    
    public String getExtBuild(){
        return _fields.get("ExtBuild");
    }
    
}
