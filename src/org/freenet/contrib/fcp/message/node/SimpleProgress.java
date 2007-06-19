/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;

/**
 * <p>This indicates the progress of a large request 
 * (usually a splitfile, a big file which is split across a large number of blocks).</p>
 * 
 * <p>
 * Note that before FinalizedTotal=true, Total may vary wildly on a 
 * {@link org.freenet.contrib.fcp.message.client.ClientGet ClientGet}, 
 * because we may follow redirects, have to fetch multi-level splitfiles and so on. 
 * However, once we are fetching the final splitfile, FinalizedTotal will be set to 
 * true. Whereas on a {@link org.freenet.contrib.fcp.message.client.ClientPut ClientPut}, 
 * we can't generate the metadata until quite late on, so it takes a long time to set 
 * FinalizedTotal=true, but this doesn't matter as the Total will not increase very much 
 * (since the majority of the insert is the actual data and check blocks).
 * </p>
 * @author Ralph Smithen
 */
public class SimpleProgress extends NodeMessage{
    
    /**
     * 
     * @inheritDoc 
     */
    protected void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getQueueEventSupport().fireSimpleProgressUpdate(this);
    }

    public int getTotal() {
        return Integer.parseInt(_fields.get("Total"));
    }

    public void setTotal(int total) {
        _fields.put("Total", String.valueOf(total));
    }

    public int getRequired() {
        return Integer.parseInt(_fields.get("Required"));
    }

    public void setRequired(int required) {
        _fields.put("Required", String.valueOf(required));
    }

    public int getFailed() {
        return Integer.parseInt(_fields.get("Failed"));
    }

    public void setFailed(int failed) {
        _fields.put("Failed", String.valueOf(failed));
    }

    public int getFatallyFailed() {
        return Integer.parseInt(_fields.get("FatallyFailed"));
    }

    public void setFatallyFailed(int fatallyFailed) {
        _fields.put("FatallyFailed", String.valueOf(fatallyFailed));
    }

    public int getSucceeded() {
        return Integer.parseInt(_fields.get("Succeeded"));
    }

    public void setSucceeded(int succeeded) {
        _fields.put("Succeeded", String.valueOf(succeeded));
    }

    public boolean isFinalizedTotal() {
        return Boolean.parseBoolean(_fields.get("FinalizedTotal"));
    }

    public void setFinalizedTotal(boolean finalizedTotal) {
         _fields.put("FinalizedTotal", String.valueOf(finalizedTotal));
    }

    public String getId() {
        return _fields.get("Identifier");
    }

    public void setId(String id) {
        _fields.put("Identifier", id);
    }

}
