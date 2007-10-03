/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.message;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.freenetproject.contrib.fcp.message.node.NodeMessage;

/**
 * Reads FCP node messages from a given stream.
 * @author Ralph Smithen
 */
public class FcpMessageInputStream {
    private static final    String          MESSAGE_PACKAGE = NodeMessage.class.getPackage().getName();
    private                 DataInputStream _dis;
    private                 String          _encoding = "UTF-8";
    
    /**
     * Creates a new instance of FcpMessageStream, wraps a buffer around the <code>InputStream</code>.
     * @param in the stream from which to read FCP data
     */
    public FcpMessageInputStream(InputStream in) {
        _dis = new DataInputStream(new BufferedInputStream(in));
    }
    
    String readLine() throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte b;
        
        while(true){
            b = _dis.readByte();
            
            if(b == '\n'){ //REDFLAG:  is this safe with other encodings? - why is encoding not in FCP specifications?
                if(bos.size() == 0){ // blank line
                    continue;
                }else{ // end of line
                    break;
                }
            }
            bos.write(b);
        }
        
        String line = bos.toString(_encoding).trim(); 
        return line.length() == 0 ? readLine() : line; // blank lines allowed for debugging - if so, read on
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
            ; // read all message fields, append data if present
        
        m.validate();
        
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
        
        byte[] data = new byte[m.getDataLength()];
        int toRead = data.length, read;
        while(toRead > 0){
            read = _dis.read(data, data.length - toRead, toRead > 4096 ? 4096 : toRead);
            toRead -= read;
        }
        m.setData(data);
        return false;
    }
    
    /**
     * Closes the delegate stream.
     * @throws java.io.IOException if thrown on underlying stream
     */
    public void close() throws IOException {
        _dis.close();
    }

    /**
     * Gets the encoding, default=UTF-8
     * @return the encoding
     */
    public String getEncoding() {
        return _encoding;
    }

    /**
     * Sets the encoding
     * @param encoding new encoding
     */
    public void setEncoding(String encoding) {
        _encoding = encoding;
    }
    
}




