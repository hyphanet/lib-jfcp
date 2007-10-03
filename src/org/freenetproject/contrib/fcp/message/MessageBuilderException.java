/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.message;

/**
 * Thrown when reading or writing {@link org.freenet.contrib.fcp.FcpMessage FcpMessage}s.
 * @author Ralph Smithen
 */
public class MessageBuilderException extends Exception{
    
    /** Creates a new instance of MessageBuilderException */
    public MessageBuilderException(String message) {
        super(message);
    }
    
}
