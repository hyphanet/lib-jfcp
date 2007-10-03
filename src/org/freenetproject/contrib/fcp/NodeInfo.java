/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp;

import org.freenetproject.contrib.fcp.message.node.Peer;

/**
 * Holds data associated with a node or peer.
 * @author Ralph Smithen
 */
public class NodeInfo {
    private String _fcpVersion;
    private String _node;
    private String _build;
    private String _extRevision;
    private boolean _testNet;
    private String _extBuild;
    private String _compressionCodecs;
    private String _revision;
    
    private String _lastGoodVersion;
    private String _physicalUdp;
    private String _id;
    private String _dsaGroupG;
    private String _name;
    private String _dsaPubKeyY;
    private String _dsaGroupQ;
    private String _arkNumber;
    private String _version;
    private String _arkPubUri;
    private String _dsaGroupP;
    private double _location;
    private boolean _testnet;
    
    /** Creates a new instance of NodeInfo */
    public NodeInfo() {
    }
    
    /**
     * Instantiates NodeInfo from Peer.
     * @param p the peer
     */
    public NodeInfo(Peer p){
        _lastGoodVersion=p.getLastGoodVersion();
        _physicalUdp=p.getPhysicalUdp();
        _id=p.getId();
        _dsaGroupG=p.getDsaGroupG();
        _name=p.getName();
        _dsaPubKeyY=p.getDsaPubKeyY();
        _dsaGroupQ=p.getDsaGroupQ();
        _arkNumber=p.getArkNumber();
        _version=p.getVersion();
        _arkPubUri=p.getArkPubUri();
        _dsaGroupP=p.getDsaGroupP();
        _location=p.getLocation();
        _testNet=p.isTestnet();
    }
    
    /**
     * Get the version of FCP for this node.
     * @return the FCP version
     */
    public String getFcpVersion() {
        return _fcpVersion;
    }
    
    /**
     * Sets teh FCP version
     * @param fcpVersion the FCP version, e.g. "2.0"
     */
    public void setFcpVersion(String fcpVersion) {
        this._fcpVersion = fcpVersion;
    }
    
    /**
     * Get Fred version
     * @return the version of Fred
     */
    public String getVersion() {
        return _version;
    }
    
    public void setVersion(String version) {
        this._version = version;
    }
    
    public String getBuild() {
        return _build;
    }
    
    public void setBuild(String build) {
        this._build = build;
    }
    
    public String getExtRevision() {
        return _extRevision;
    }
    
    public void setExtRevision(String extRevision) {
        this._extRevision = extRevision;
    }
    
    public boolean isTestNet() {
        return _testNet;
    }
    
    public void setTestNet(boolean testNet) {
        this._testNet = testNet;
    }
    
    public String getExtBuild() {
        return _extBuild;
    }
    
    public void setExtBuild(String extBuild) {
        this._extBuild = extBuild;
    }
    
    public String getCompressionCodecs() {
        return _compressionCodecs;
    }
    
    public void setCompressionCodecs(String compressionCodecs) {
        this._compressionCodecs = compressionCodecs;
    }
    
    public String getRevision() {
        return _revision;
    }
    
    public void setRevision(String revision) {
        this._revision = revision;
    }
    
    public String getPhysicalUdp() {
        return _physicalUdp;
    }
    
    public void setPhysicalUdp(String physicalUdp) {
        this._physicalUdp = physicalUdp;
    }
    
    public String getLastGoodVersion() {
        return _lastGoodVersion;
    }
    
    public void setLastGoodVersion(String lastGoodVersion) {
        this._lastGoodVersion = lastGoodVersion;
    }
    
    public String getArkPubUri() {
        return _arkPubUri;
    }
    
    public void setArkPubUri(String arkPubUri) {
        this._arkPubUri = arkPubUri;
    }
    
    public String getArkNumber() {
        return _arkNumber;
    }
    
    public void setArkNumber(String arkNumber) {
        this._arkNumber = arkNumber;
    }
    
    public String getId() {
        return _id;
    }
    
    public void setId(String id) {
        this._id = id;
    }
    
    public String getName() {
        return _name;
    }
    
    public void setName(String name) {
        this._name = name;
    }
    
    public double getLocation() {
        return _location;
    }
    
    public void setLocation(double location) {
        this._location = location;
    }
    
    public String getDsaGroupG() {
        return _dsaGroupG;
    }
    
    public void setDsaGroupG(String dsaGroupG) {
        this._dsaGroupG = dsaGroupG;
    }
    
    public String getDsaPubKeyY() {
        return _dsaPubKeyY;
    }
    
    public void setDsaPubKeyY(String dsaPubKeyY) {
        this._dsaPubKeyY = dsaPubKeyY;
    }
    
    public String getDsaGroupQ() {
        return _dsaGroupQ;
    }
    
    public void setDsaGroupQ(String dsaGroupQ) {
        this._dsaGroupQ = dsaGroupQ;
    }
    
    public String getDsaGroupP() {
        return _dsaGroupP;
    }
    
    public void setDsaGroupP(String dsaGroupP) {
        this._dsaGroupP = dsaGroupP;
    }
    
    public boolean isTestnet() {
        return _testnet;
    }
    
    public void setTestnet(boolean testnet) {
        this._testnet = testnet;
    }
    
    public String toString(){
        return getName();
    }
    
}
