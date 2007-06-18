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
 * The superclass of all FCP messages.
 * @author Ralph Smithen
 */
public abstract class FcpMessage {
    /**
     * the name=value pairs of the message
     */
    protected Map<String, String> _fields = new HashMap();
    /**
     * the name of the message
     */
    protected String _headerString;
    
    /**
     * Creates new instance, assigns {@link #_headerString _headerString}.
     */
    public FcpMessage(){
        _headerString = this.getClass().getSimpleName();
    }
    
    /**
     * Accessor for message's fields.
     * @return the message fields
     */
    public Map<String, String> getFields() {
        return _fields;
    }
    
    /**
     * Get the name of the message.
     * @return the name
     */
    public String getHeaderString(){
        return _headerString;
    }
    
    /**
     * Trigger the events upon receipt or transmission of a message.
     * @param eventSupport the object to be notified of message events
     */
    public abstract void fireEvents(FcpEventSupportRepository eventSupport);
      
    /**
     * Get the fields that must be non-null in a valid message.
     * @return an array of <code>String</code>s, the names of the mandatory fields
     */
    public String[] getMandatoryFields() {
        return new String[] {};
    }
    
    /**
     * Stream the message.
     * @param out the stream to be written to
     */
    public void writeMessage(PrintStream out){
        out.println(_headerString);
        for(Map.Entry entry : _fields.entrySet()){
            out.println(entry.getKey() + "=" + entry.getValue());
        }
        out.println("EndMessage");
    }
    
    
    /**
     * Get the text representation of the message.
     * @return a <code>String</code> of the message's FCP representation
     */
    public String getMessageString(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        writeMessage(new PrintStream(baos));
        return baos.toString();
    }
}
