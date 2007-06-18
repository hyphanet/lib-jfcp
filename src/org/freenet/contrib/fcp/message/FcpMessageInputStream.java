/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.freenet.contrib.fcp.message.DataHoldingMessage;
import org.freenet.contrib.fcp.message.node.NodeMessage;

/**
 * Reads FCP node messages from a given stream.
 * @author Ralph Smithen
 */
public class FcpMessageInputStream {
    private static final String MESSAGE_PACKAGE = NodeMessage.class.getPackage().getName();
    private DataInputStream _dis;
    
    /**
     * Creates a new instance of FcpMessageStream, wraps a buffer around the <code>InputStream</code>.
     * @param in the stream from which to read FCP data
     */
    public FcpMessageInputStream(InputStream in) {
        _dis = new DataInputStream(new BufferedInputStream(in));
    }
    
    String readLine() throws IOException{
        StringBuffer sb = new StringBuffer();
        byte b;
        
        while(true){
            b = _dis.readByte();
            
            if(b == '\n'){
                if(sb.length() == 0){ // blank line
                    continue;
                }else{ // end of line
                    break;
                }
            }
            sb.append((char) b);
        }
        String line = sb.toString();
        return line.trim().length() == 0 ? readLine() : line;
    }
    
    /**
     * Reads and returns the next message.
     * @return the next message
     * @throws java.io.IOException if thrown on underlying stream
     * @throws org.freenet.contrib.fcp.message.MessageBuilderException if the data seems garbled
     */
    public NodeMessage readMessage() throws IOException, MessageBuilderException{
        NodeMessage m;
        String line = readLine();
        
        try {
            m = (NodeMessage) Class.forName(MESSAGE_PACKAGE + "." + line).newInstance();
        } catch (Exception ex) {
            throw new MessageBuilderException("unknown message header (" + line + ")");
        }
        while(readField(m))
            ; // read all message fields
        
        String[] mandatoryFields = m.getMandatoryFields();
        
        for(String field : mandatoryFields){
            
            if(m.getFields().get(field) == null)
                throw new MessageBuilderException(
                        "mandatory field " + field + " not found in message " + 
                        m.getHeaderString());
        }
        
        return m;
    }
    
    boolean readField(NodeMessage m) throws IOException, MessageBuilderException{
        String line = readLine();
        
        int pos = line.indexOf('=');
        
        if(pos > 0){ //set field value
            m.getFields().put(line.substring(0, pos), line.substring(pos + 1));
            return true;
        }
        
        if(line.equals("EndMessage"))
            return false;
        
        if(!line.equals("Data")){
            throw new MessageBuilderException(
                    "unknown tag (" + line + ") in message " + m.getHeaderString());
        }
        
        DataHoldingMessage dhm = (DataHoldingMessage) m;
        
        byte[] data = new byte[dhm.getDataLength()];
        _dis.read(data, 0, data.length);
        
        dhm.setData(data);
        return false;
    }

    /**
     * Closes the delegate stream.
     * @throws java.io.IOException if thrown on underlying stream
     */
    public void close() throws IOException {
        _dis.close();
    }
    
}




