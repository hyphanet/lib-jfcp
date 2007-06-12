/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *
 * @author res
 */
public abstract class FcpMessage {
    protected Map<String, String> _fields = new HashMap();
    protected String _headerString;
    
    public FcpMessage(){
//        String name = this.getClass().getName();
//        _headerString = name.substring(name.lastIndexOf('.') + 1);
        _headerString = this.getClass().getSimpleName();
    }
    
    public Map<String, String> getFields() {
        return _fields;
    }
    
    public String getHeaderString(){
        return _headerString;
    }
    
    public abstract void fireEvents(FcpEventSupportRepository eventSupport);
      
    public String[] getMandatoryFields() {
        return new String[] {};
    }
    
    public void writeMessage(PrintStream out){
        out.println(_headerString);
        for(Map.Entry entry : _fields.entrySet()){
            out.println(entry.getKey() + "=" + entry.getValue());
        }
        out.println("EndMessage");
    }
    
    
    public String getMessageString(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        writeMessage(new PrintStream(baos));
        return baos.toString();
    }
}
