/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.event;

import java.util.Date;

/**
 *
 * @author res
 */
abstract class FcpEvent<MessageType> {
    protected Date timeStamp = new Date();
    private MessageType _message;
    
    /** Creates a new instance of FcpEvent */
    public FcpEvent() {
    }
    
    /** Creates a new instance of FcpNodeMessageEvent */
    public FcpEvent(MessageType message) {
        _message = message;
    }
    
    public MessageType getMessage() {
        return _message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
}
