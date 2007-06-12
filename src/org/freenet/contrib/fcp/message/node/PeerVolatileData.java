/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

/**
 *
 * @author res
 */
public class PeerVolatileData {
    private Peer _p;
    
    public enum Status{
        CONNECTED,
        DISCONNECTED,
        BACKED_OFF,
        TOO_OLD,
        NEVER_CONNECTED
    }
    
    public enum LastRoutingBackoffReason{
        AcceptedTimeout,
        FatalTimeout,
        ForwardRejectedOverload,
        ForwardRejectedOverload2
    }
    
//        volatile.averagePingTime=1.0
//        volatile.overloadProbability=0.0
//        volatile.idle=791670007
//        volatile.percentTimeRoutableConnection=17.58324201298154
//        volatile.routingBackoffPercent=0.0
//        volatile.status=DISCONNECTED
//        volatile.totalBytesIn=0
//        volatile.routingBackoffLength=1000
//        volatile.routingBackoff=0
//        volatile.totalBytesOut=8532482
    
    
//            volatile.averagePingTime=375.7280706108754
//            volatile.overloadProbability=0.262501082516091
//            volatile.percentTimeRoutableConnection=73.87877919472704
//            volatile.routingBackoffPercent=0.4766362445492835
//            volatile.status=CONNECTED
//            volatile.totalBytesIn=185355902
//            volatile.routingBackoffLength=1000
//            volatile.lastRoutingBackoffReason=FatalTimeout
//            volatile.routingBackoff=0
//            volatile.totalBytesOut=197757028
    
    
//                volatile.averagePingTime=5869.929339951476
//                volatile.overloadProbability=21.63939255794491
//                volatile.idle=89079368
//                volatile.percentTimeRoutableConnection=49.16023336672756
//                volatile.routingBackoffPercent=50.18356974749365
//                volatile.status=DISCONNECTED
//                volatile.totalBytesIn=30081871
//                volatile.routingBackoffLength=256000
//                volatile.lastRoutingBackoffReason=AcceptedTimeout
//                volatile.routingBackoff=0
//                volatile.totalBytesOut=72200884
    
    
    
    /** Creates a new instance of PeerVolatileData */
    public PeerVolatileData(Peer p) {
        _p = p;
    }

    public float getAveragePingTime() {
        return Float.parseFloat(_p.getFields().get("volatile.averagePingTime"));
    }

    public void setAveragePingTime(float averagePingTime) {
         _p.getFields().put("volatile.averagePingTime", String.valueOf(averagePingTime));
    }

    public float getOverloadProbability() {
        return Float.parseFloat(_p.getFields().get("volatile.overloadProbability"));
    }

    public void setOverloadProbability(float overloadProbability) {
         _p.getFields().put("volatile.overloadProbability", String.valueOf(overloadProbability));
    }

    public int getIdle() {
        return Integer.parseInt(_p.getFields().get("volatile.idle"));
    }

    public void setIdle(int idle) {
         _p.getFields().put("volatile.idle", String.valueOf(idle));
    }

    public float getPercentTimeRoutableConnection() {
        return Float.parseFloat(_p.getFields().get("volatile.percentTimeRoutableConnection"));
    }

    public void setPercentTimeRoutableConnection(float percentTimeRoutableConnection) {
         _p.getFields().put("volatile.percentTimeRoutableConnection", String.valueOf(percentTimeRoutableConnection));
    }

    public float getRoutingBackoffPercent() {
        return Float.parseFloat(_p.getFields().get("volatile.routingBackoffPercent"));
    }

    public void setRoutingBackoffPercent(float routingBackoffPercent) {
         _p.getFields().put("volatile.routingBackoffPercent", String.valueOf(routingBackoffPercent));
    }

    public Status getStatus() {
        return Status.valueOf(_p.getFields().get("volatile.status").replace(' ', '_'));
    }

    public void setStatus(Status status) {
        _p.getFields().put("volatile.status", status.toString().replace('_', ' '));
    }

    public long getTotalBytesIn() {
        return Long.parseLong(_p.getFields().get("volatile.totalBytesIn"));
    }

    public void setTotalBytesIn(long totalBytesIn) {
        _p.getFields().put("volatile.totalBytesIn", String.valueOf(totalBytesIn));
    }

    public long getRoutingBackoffLength() {
        return Long.parseLong(_p.getFields().get("volatile.routingBackoffLength"));
    }

    public void setRoutingBackoffLength(long routingBackoffLength) {
        _p.getFields().put("volatile.routingBackoffLength", String.valueOf(routingBackoffLength));
    }

    public LastRoutingBackoffReason getLastRoutingBackoffReason() {
        return LastRoutingBackoffReason.valueOf(_p.getFields().get("volatile.lastRoutingBackoffReason"));
    }

    public void setLastRoutingBackoffReason(LastRoutingBackoffReason lastRoutingBackoffReason) {
        _p.getFields().put("volatile.lastRoutingBackoffReason", lastRoutingBackoffReason.toString());
    }

    public long getRoutingBackoff() {
        return Long.parseLong(_p.getFields().get("volatile.routingBackoff"));
    }

    public void setRoutingBackoff(long routingBackoff) {
        _p.getFields().put("volatile.routingBackoff", String.valueOf(routingBackoff));
    }

    public long getTotalBytesOut() {
        return Long.parseLong(_p.getFields().get("volatile.totalBytesOut"));
    }

    public void setTotalBytesOut(long totalBytesOut) {
        _p.getFields().put("volatile.totalBytesOut", String.valueOf(totalBytesOut));
    }
    
    
    
}
