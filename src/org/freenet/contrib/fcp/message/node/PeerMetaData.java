/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

/**
 *
 * @author res
 */
public class PeerMetaData {
    private Peer _p;
    
//            metadata.routableConnectionCheckCount=118630
//            metadata.hadRoutableConnectionCount=20859
//            metadata.timeLastConnected=1173191216637
//            metadata.timeLastRoutable=1173191216637
//            metadata.timeLastReceivedPacket=1173191156574
//            metadata.detected.udp=130.89.162.43:50591
    
    public PeerMetaData(Peer p){
        _p = p;
    }

    public int getRoutableConnectionCheckCount() {
        return Integer.parseInt(_p.getFields().get("metadata.routableConnectionCheckCount"));
    }

    public void setRoutableConnectionCheckCount(int routableConnectionCheckCount) {
        _p.getFields().put("metadata.routableConnectionCheckCount", String.valueOf(routableConnectionCheckCount));
    }

    public int getHadRoutableConnectionCount() {
        return Integer.parseInt(_p.getFields().get("metadata.hadRoutableConnectionCount"));
    }

    public void setHadRoutableConnectionCount(int hadRoutableConnectionCount) {
        _p.getFields().put("metadata.hadRoutableConnectionCount", String.valueOf(hadRoutableConnectionCount));
    }

    public int getTimeLastConnected() {
        return Integer.parseInt(_p.getFields().get("metadata.timeLastConnected"));
    }

    public void setTimeLastConnected(int timeLastConnected) {
        _p.getFields().put("metadata.timeLastConnected", String.valueOf(timeLastConnected));
    }

    public int getTimeLastRoutable() {
        return Integer.parseInt(_p.getFields().get("metadata.timeLastRoutable"));
    }

    public void setTimeLastRoutable(int timeLastRoutable) {
        _p.getFields().put("metadata.timeLastRoutable", String.valueOf(timeLastRoutable));
    }

    public int getTimeLastReceivedPacket() {
        return Integer.parseInt(_p.getFields().get("metadata.timeLastReceivedPacket"));
    }

    public void setTimeLastReceivedPacket(int timeLastReceivedPacket) {
        _p.getFields().put("metadata.timeLastReceivedPacket", String.valueOf(timeLastReceivedPacket));
    }

    public String getDetectedUdp() {
        return _p.getFields().get("metadata.detected.udp");
    }

    public void setDetectedUdp(String detectedUdp) {
        _p.getFields().put("metadata.detected.udp", detectedUdp);
    }
    
}
