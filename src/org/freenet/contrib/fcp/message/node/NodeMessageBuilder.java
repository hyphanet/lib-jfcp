/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;
import org.freenet.contrib.fcp.FcpConnection;
import org.freenet.contrib.fcp.message.FcpMessage;
import org.freenet.contrib.fcp.message.MessageBuilderException;

/**
 *
 * @author res
 */
public class NodeMessageBuilder {
    private static Logger logger = Logger.getLogger(NodeMessageBuilder.class.getName());
    // FcpMessage
    private NodeMessage _message;
    
    public void parse(BufferedReader in) throws MessageBuilderException, IOException{
        String line;
        boolean firstLine = true;
        
        while((line = in.readLine()) != null){
            
            if(line.trim().equals(""))
                continue; // blank lines allowed for debugging
            
            
            if(firstLine){
                try {
                    _message = (NodeMessage) Class.forName(
                            this.getClass().getPackage().getName() + "." + line).newInstance();
                    firstLine = false;
                } catch (Exception ex) {
                    throw new MessageBuilderException("unknown message header (" + line + ")");
                }
                continue;
            }
            
            if(line.equals("EndMessage"))
                break;
            
            
            int pos = line.indexOf('=');
            if(pos < 0){
                if(line.equals("Data") && _message instanceof DataHoldingMessage){
                    
                    DataHoldingMessage m = (DataHoldingMessage) _message;
                    char[] data = new char[m.getDataLength()];
                    StringBuffer sb = new StringBuffer();
                    char[] b = new char[4096];
                    int read = 0, toRead = m.getDataLength();
                    while(toRead > 0 && 
                            (read = in.read(b, 0, toRead > b.length ? b.length : toRead)) != -1){
                        sb.append(b, 0, read);
                        toRead -= read;
                    }
                    m.setData(sb.toString());
                    break;
                    
                }else{
                    throw new MessageBuilderException(
                            "unknown tag (" + line + ") in message " + _message.getHeaderString());
                }
                
            }else
               _message.getFields().put(line.substring(0, pos), line.substring(pos + 1));
        }
        
        
    }
    
    
    public NodeMessage build() throws MessageBuilderException{
        String[] mandatoryFields = _message.getMandatoryFields();
        
        for(String field : mandatoryFields){
            if(((FcpMessage) _message).getFields().get(field) == null)
                throw new MessageBuilderException(
                        "mandatory field " + field + " not found in message " + ((FcpMessage) _message).getHeaderString()
                        );
        }
        
        return _message;
    }
}
