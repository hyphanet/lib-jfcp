/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.client;

import java.io.PrintStream;
import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 *
 * @author res
 */
public class ClientHello extends ClientMessage{
    
    public ClientHello(String expectedVersion, String clientName){
        setExpectedVersion(expectedVersion);
        setName(clientName);
    }

    public String[] getMandatoryFields() {
        return new String[] { "Name", "ExpectedVersion" };
    }

    public String getName() {
        return _fields.get("Name");
    }

    public void setName(String name) {
        _fields.put("Name", name);
    }

    public String getExpectedVersion() {
        return _fields.get("ExpectedVersion");
    }

    public void setExpectedVersion(String expectedVersion) {
        _fields.put("ExpectedVersion", expectedVersion);
    }

    public void fireEvents(FcpEventSupportRepository eventSupport) {
    }
    
}
