/*
 * FreenetClientTest.java
 * JUnit based test
 *
 * Created on 17 June 2007, 15:40
 */

package org.freenet.contrib.fcp;

import java.util.Map;
import java.util.logging.Logger;
import junit.framework.*;
import org.freenet.contrib.fcp.event.AllDataEvent;
import org.freenet.contrib.fcp.event.DataFoundEvent;
import org.freenet.contrib.fcp.event.FcpConnectEvent;
import org.freenet.contrib.fcp.event.FcpDisconnectEvent;
import org.freenet.contrib.fcp.event.FcpKeyRequestedEvent;
import org.freenet.contrib.fcp.event.FcpPeerListUpdatedEvent;
import org.freenet.contrib.fcp.event.FcpSimpleProgressEvent;
import org.freenet.contrib.fcp.event.GetFailedEvent;
import org.freenet.contrib.fcp.event.SSKKeypairEvent;
import org.freenet.contrib.fcp.event.support.FcpEventSource;
import org.freenet.contrib.fcp.listener.FcpConnectionListener;
import org.freenet.contrib.fcp.listener.FcpPeerListListener;
import org.freenet.contrib.fcp.listener.FcpQueueListener;
import org.freenet.contrib.fcp.listener.FcpSSKKeypairListener;
import org.freenet.contrib.fcp.message.node.Peer;

/**
 *
 * @author ralph
 */
public class FreenetClientTest extends TestCase
        implements FcpConnectionListener, FcpPeerListListener, FcpQueueListener, FcpSSKKeypairListener{
    FreenetClient instance;
    public FreenetClientTest(String testName) throws Exception {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        System.out.println("setUp");
        instance = new FreenetClient(new NodeAddress(9482), "FreenetClientTests");
        instance.getEventSource().addPeerListListener(this);
        instance.getEventSource().addConnectionListener(this);
        instance.getEventSource().addQueueListener(this);
        instance.getEventSource().addKeypairListener(this);
        instance.getConnection().open();
    }
    
    protected void tearDown() throws Exception {
        System.out.println("tearDown");
        instance.getConnection().close();
    }
    
    private void myWait(){
        synchronized(this){
            try {
                System.out.println("waiting for response...");
                wait();
            } catch (InterruptedException ignore) {}
        }
    }
    
    /**
     * Test of getEventSource method, of class org.freenet.contrib.fcp.FreenetClient.
     */
    public void testGetEventSource() {
        System.out.println("getEventSource");
        
        FcpEventSource result = instance.getEventSource();
        assertNotNull(result);
    }
    
    /**
     * Test of getConnection method, of class org.freenet.contrib.fcp.FreenetClient.
     */
    public void testGetConnection() {
        System.out.println("getConnection");
        FcpConnection result = instance.getConnection();
        assertTrue(result.isSocketOpen());
    }
    
    /**
     * Test of refreshPeerList method, of class org.freenet.contrib.fcp.FreenetClient.
     */
    public void testRefreshPeerList() {
        System.out.println("refreshPeerList");
        instance.refreshPeerList();
        
        myWait();
        
    }
    
    /**
     * Test of get method, of class org.freenet.contrib.fcp.FreenetClient.
     */
    public void testGet() {
        System.out.println("get");
        
        String uri = "CHK@RtxdVnUQuTEZwHuUjpHG0tZLQuVP~QsDDn5v8e-9xQk,o1fhtMW6jE29IHs0GSYk-~-ex~pTW5elWQYJ5FVH5qc,AAIC--8/1.html";
        String id = "yamas1";
        
        instance.get(uri, id);
        
        myWait();
    }
    
    /**
     * Test of generateSSK method, of class org.freenet.contrib.fcp.FreenetClient.
     */
    public void testGenerateSSK() {
        System.out.println("generateSSK");
        
        String id = "mytest";
        instance.generateSSK(id);
        
        myWait();
        
    }
    
    public void peerListUpdated(FcpPeerListUpdatedEvent e) {
        System.out.println("peerListUpdated, peer list:");
        for(Map.Entry<String, Peer> entry : e.getPeers().entrySet()){
            System.out.println("  > " + entry.getValue().getName() + "");
        }
        synchronized(this){
            notify();
        }
    }
    
    public void nodeConnected(FcpConnectEvent e) {
        System.out.println("nodeConnected");
    }
    
    public void nodeDisconnected(FcpDisconnectEvent e) {
        System.out.println("nodeDisconnected");
    }
    
    public void simpleProgressUpdate(FcpSimpleProgressEvent e) {
        System.out.println("simpleProgressUpdate");
    }
    
    public void keyRequested(FcpKeyRequestedEvent e) {
        System.out.println("keyRequested");
    }
    
    public void dataFound(DataFoundEvent e) {
        System.out.println("dataFound");
    }
    
    public void allData(AllDataEvent e) {
        System.out.println("allData");
        System.out.println(new String(e.getMessage().getData()));
        synchronized(this){
            notify();
        }
    }
    
    public void getFailed(GetFailedEvent e) {
        System.out.println("getFailed");
        
        synchronized(this){
            notify();
        }
    }
    
    public void keypairReceived(SSKKeypairEvent kpe) {
        System.out.println("keypair:");
        System.out.println("  id=" + kpe.getMessage().getId());
        System.out.println("  insert=" + kpe.getMessage().getInsertURI());
        System.out.println("  request=" + kpe.getMessage().getRequestURI());
        
        
        synchronized(this){
            notify();
        }
    }
    
}
