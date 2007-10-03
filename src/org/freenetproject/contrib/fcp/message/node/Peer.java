/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.message.node;

import org.freenetproject.contrib.fcp.NodeInfo;
import org.freenetproject.contrib.fcp.event.support.FcpEventSupportRepository;
import org.freenetproject.contrib.fcp.peer.PeerMetaData;
import org.freenetproject.contrib.fcp.peer.PeerVolatileData;

/**
 *This gives the details of a Freenet node that is directly connected to your node (a peer or friend).
 * @author Ralph Smithen
 */
public class Peer extends NodeMessage{
    private PeerMetaData peerMetaData;
    private PeerVolatileData peerVolatileData;
    
    public Peer(){
        peerMetaData = new PeerMetaData(this);
        peerVolatileData = new PeerVolatileData(this);
    }
    
    /**
     * @inheritDoc 
     */
    protected void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getPeerListEventSupport().firePeerUpdated(this);
    }

    public String getLastGoodVersion() {
        return _fields.get("lastGoodVersion");
    }

    public void setLastGoodVersion(String lastGoodVersion) {
        _fields.put("lastGoodVersion", lastGoodVersion);
    }

    public String getPhysicalUdp() {
        return _fields.get("physical.udp");
    }

    public void setPhysicalUdp(String physicalUdp) {
        _fields.put("physical.udp", physicalUdp);
    }

    public String getId() {
        return _fields.get("identity");
    }

    public void setId(String identity) {
        _fields.put("identity", identity);
    }

    public String getDsaGroupG() {
        return _fields.get("dsaGroup.g");
    }

    public void setDsaGroupG(String dsaGroupG) {
        _fields.put("dsaGroup.g", dsaGroupG);
    }

    public String getName() {
        return _fields.get("myName");
    }

    public void setName(String name) {
        _fields.put("myName", name);
    }

    public String getDsaPubKeyY() {
        return _fields.get("dsaPubKey.y");
    }

    public void setDsaPubKeyY(String dsaPubKeyY) {
        _fields.put("dsaPubKey.y", dsaPubKeyY);
    }

    public String getDsaGroupQ() {
        return _fields.get("dsaGroup.q");
    }

    public void setDsaGroupQ(String dsaGroupQ) {
        _fields.put("dsaGroup.q", dsaGroupQ);
    }

    public String getArkNumber() {
        return _fields.get("ark.number");
    }

    public void setArkNumber(String arkNumber) {
        _fields.put("ark.number", arkNumber);
    }

    public String getVersion() {
        return _fields.get("version");
    }

    public void setVersion(String version) {
        _fields.put("version", version);
    }

    public String getArkPubUri() {
        return _fields.get("ark.pubURI");
    }

    public void setArkPubUri(String arkPubUri) {
        _fields.put("ark.pubURI", arkPubUri);
    }

    public String getDsaGroupP() {
        return _fields.get("dsaGroup.p");
    }

    public void setDsaGroupP(String dsaGroupP) {
        _fields.put("dsaGroup.p", dsaGroupP);
    }

    public double getLocation() {
        return Double.parseDouble(_fields.get("location"));
    }

    public void setLocation(double location) {
        _fields.put("location", String.valueOf(location));
    }

    public boolean isTestnet() {
        return Boolean.parseBoolean(_fields.get("testnet"));
    }

    public void setTestnet(boolean testnet) {
        _fields.put("_testnet", String.valueOf(testnet));
    }

    public PeerMetaData getPeerMetaData() {
        return peerMetaData;
    }

    public PeerVolatileData getPeerVolatileData() {
        return peerVolatileData;
    }

    
    public String toString(){
        return getName();
    }
}
