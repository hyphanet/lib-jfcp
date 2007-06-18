/*
 * FcpMessageOutputStream.java
 *
/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import org.freenet.contrib.fcp.message.client.ClientMessage;

/**
 *
 * @author Ralph Smithen
 */
public class FcpMessageOutputStream {
    private DataOutputStream _dos;
    /** Creates a new instance of FcpMessageOutputStream */
    public FcpMessageOutputStream(OutputStream out) {
        _dos = new DataOutputStream(new BufferedOutputStream(out));
    }
    
    public void writeMessage(ClientMessage m) throws MessageBuilderException, IOException{
        m.validate();
        
        writeLine(m.getHeaderString());
        
        for(Map.Entry<String, String> field : m.getFields().entrySet()){
            writeField(field.getKey(), field.getValue());
        }
        if(m instanceof DataHoldingMessage){
            writeLine("Data");
            _dos.write(((DataHoldingMessage) m).getData());
        }else{
            writeLine("EndMessage");
        }
        
    }
    
    void writeField(String name, String value) throws IOException{
        _dos.writeBytes(name);
        _dos.writeByte('=');
        _dos.writeBytes(value);
        _dos.writeByte('\n');
    }
    
    void writeLine(String line) throws IOException{
        _dos.writeBytes(line);
        _dos.writeByte('\n');
    }
    
    public void flush() throws IOException{
        _dos.flush();
    }
    
    public void close() throws IOException{
        _dos.close();
    }
}
