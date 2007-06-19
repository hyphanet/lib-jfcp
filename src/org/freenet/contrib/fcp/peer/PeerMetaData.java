/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.peer;

import java.util.Map;
import org.freenet.contrib.fcp.message.node.*;

/**
 * Helper class for {@link org.freenet.contrib.fcp.message.node.Peer Peer}.
 * @author Ralph Smithen
 */
public class PeerMetaData {
    private Map<String, String> _fields;
    
//            metadata.routableConnectionCheckCount=118630
//            metadata.hadRoutableConnectionCount=20859
//            metadata.timeLastConnected=1173191216637
//            metadata.timeLastRoutable=1173191216637
//            metadata.timeLastReceivedPacket=1173191156574
//            metadata.detected.udp=130.89.162.43:50591
    
    public PeerMetaData(Peer p){
        _fields = p.getFields();
    }

    public int getRoutableConnectionCheckCount() {
        return Integer.parseInt(_fields.get("metadata.routableConnectionCheckCount"));
    }

    public void setRoutableConnectionCheckCount(int routableConnectionCheckCount) {
        _fields.put("metadata.routableConnectionCheckCount", String.valueOf(routableConnectionCheckCount));
    }

    public int getHadRoutableConnectionCount() {
        return Integer.parseInt(_fields.get("metadata.hadRoutableConnectionCount"));
    }

    public void setHadRoutableConnectionCount(int hadRoutableConnectionCount) {
        _fields.put("metadata.hadRoutableConnectionCount", String.valueOf(hadRoutableConnectionCount));
    }

    public int getTimeLastConnected() {
        return Integer.parseInt(_fields.get("metadata.timeLastConnected"));
    }

    public void setTimeLastConnected(int timeLastConnected) {
        _fields.put("metadata.timeLastConnected", String.valueOf(timeLastConnected));
    }

    public int getTimeLastRoutable() {
        return Integer.parseInt(_fields.get("metadata.timeLastRoutable"));
    }

    public void setTimeLastRoutable(int timeLastRoutable) {
        _fields.put("metadata.timeLastRoutable", String.valueOf(timeLastRoutable));
    }

    public int getTimeLastReceivedPacket() {
        return Integer.parseInt(_fields.get("metadata.timeLastReceivedPacket"));
    }

    public void setTimeLastReceivedPacket(int timeLastReceivedPacket) {
        _fields.put("metadata.timeLastReceivedPacket", String.valueOf(timeLastReceivedPacket));
    }

    public String getDetectedUdp() {
        return _fields.get("metadata.detected.udp");
    }

    public void setDetectedUdp(String detectedUdp) {
        _fields.put("metadata.detected.udp", detectedUdp);
    }
    
}
