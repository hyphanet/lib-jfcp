/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.message;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import org.freenetproject.contrib.fcp.message.client.ClientMessage;

/**
 * Writes FCP messages to provided stream.
 * @author Ralph Smithen
 */
public class FcpMessageOutputStream {
    private DataOutputStream _dos;
    private Writer _out;
    /**
     * Creates a new instance of FcpMessageOutputStream
     * @param out the stream to write messages to
     */
    public FcpMessageOutputStream(OutputStream out) {
        _dos = new DataOutputStream(new BufferedOutputStream(out));
        _out = new BufferedWriter(new OutputStreamWriter(_dos));
    }
    
    /**
     * Writes a message to the output stream.
     * @param m the message to be written
     * @throws org.freenet.contrib.fcp.message.MessageBuilderException if data seems incomplete or otherwise garbled
     * @throws java.io.IOException if thrown by underlying stream
     */
    public void writeMessage(ClientMessage m) throws MessageBuilderException, IOException{
        m.validate();
        
        writeLine(m.getHeaderString());
        
        for(Map.Entry<String, String> field : m.getFields().entrySet()){
            writeField(field.getKey(), field.getValue());
        }
        if(m.isData()){
            writeLine("Data");
            _out.flush();
            _dos.write(m.getData());
        }else{
            writeLine("EndMessage");
        }
        
    }
    
    void writeField(String name, String value) throws IOException{
        _out.write(name);
        _out.write('=');
        _out.write(value);
        _out.write('\n');
    }
    
    void writeLine(String line) throws IOException{
        _out.write(line);
        _out.write('\n');
    }
    
    /**
     * Flushes data to stream.
     * @throws java.io.IOException if thrown on delegate stream
     */
    public void flush() throws IOException{
        _out.flush();
    }
    
    /**
     * Closes the connection.
     * @throws java.io.IOException if thrown on delegate stream
     */
    public void close() throws IOException{
        _out.close();
    }
}
