/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.message.client;

import org.freenetproject.contrib.fcp.event.support.FcpEventSupportRepository;
import org.freenetproject.contrib.fcp.message.Persistence;
import org.freenetproject.contrib.fcp.message.UploadFrom;

/**
 * <p>This inserts an entire on-disk directory (including subdirectories) under a single key (technically, 
 * as a manifest file).  Each of the inserted files is located using the same key like this:</p>
 *<pre>
 *CHK@NOSdw7FF88S....4BgOPxSPqv~bNg7YsgM,AAEC--8/file1.txt
 *CHK@NOSdw7FF88S....4BgOPxSPqv~bNg7YsgM,AAEC--8/file2.jpg
 *CHK@NOSdw7FF88S....4BgOPxSPqv~bNg7YsgM,AAEC--8/subdir/file3.html
 *CHK@NOSdw7FF88S....4BgOPxSPqv~bNg7YsgM,AAEC--8/subdir/file4.ogg
 *CHK@NOSdw7FF88S....4BgOPxSPqv~bNg7YsgM,AAEC--8/foo/bar/file5.pdf
 *</pre>
 *
 *
 *<p>Most of the fields have the same usage as in {@link ClientPut ClientPut}.</p>
 *
 * @author Ralph Smithen
 */
public class ClientPutDiskDir extends ClientMessage{
    private byte[] _data;
    private static String[] _mandatoryFields;
       
    
    /**
     * Creates a ClientPut for direct data sending.
     * @param uri 
     * @param id 
     * @param data 
     */
    public ClientPutDiskDir(String uri, String id, byte[] data){
        setUri(uri);
        setId(id);
    }
    
    /**
     * 
     * @inheritDoc 
     */
    public String[] getMandatoryFields() {
        return _mandatoryFields;
    }

    protected void fireEvents(FcpEventSupportRepository eventSupport) {
    }

    
    public String getUri() {
        return _fields.get("URI");
    }
    
    /**
     * The type of key to insert. When inserting an SSK key, you explicitly specifiy the version 
     * number. For a USK key, use a zero and it should automatically use the correct version number.
     * @param uri 
     */
    public void setUri(String uri) {
        _fields.put("URI", uri);
    }
    
    public String getId() {
        return _fields.get("Identifier");
    }
    
    /**
     * This is just for client to be able to identify files that have been inserted.
     * @param id 
     */
    public void setId(String id) {
        _fields.put("Identifier", id);
    }
    
    public String getContentType() {
        return _fields.get("Metadata.ContentType");
    }
    
    /**
     * The MIME type of the data being inserted. For text, if charset is not specified, node should 
     * auto-detect it and force the auto-detected version
     * @param contentType 
     */
    public void setContentType(String contentType) {
        _fields.put("Metadata.ContentType", contentType);
    }
    
    public int getVerbosity() {
        return Integer.parseInt(_fields.get("Verbosity"));
    }
    
    /**
     * <pre>
     *  0: report when complete, 
     *  1: SimpleProgress messages,
     * 512: send StartedCompression and FinishedCompression messages
     * </pre>
     * @param verbosity 
     */
    public void setVerbosity(int verbosity) {
        _fields.put("Verbosity", String.valueOf(verbosity));
    }
    
    public int getMaxRetries() {
        return Integer.parseInt(_fields.get("MaxRetries"));
    }
    
    /**
     * Number of times to retry if the first time doesn't work. -1 means retry forever.
     * @param maxRetries 
     */
    public void setMaxRetries(int maxRetries) {
        _fields.put("MaxRetries", String.valueOf(maxRetries));
    }
    
    public int getPriority() {
        return Integer.parseInt(_fields.get("PriorityClass"));
    }
    
    public void setPriority(int priority) {
        _fields.put("PriorityClass", String.valueOf(priority));
    }
    
    public boolean isCHKOnly() {
        return Boolean.parseBoolean(_fields.get("GetCHKOnly"));
    }
    
    /**
     * <p>
     *    If set to true, it won't actually insert the data, 
     *    just return the key it would generate.
     * </p>
     * 
     * <p>
     *    If the key is USK, you may want to transform it into a SSK, to prevent the node 
     *    spending time searching for an unused index.
     * </p>
     * @param chk 
     */
    public void setCHKOnly(boolean chk) {
        _fields.put("GetCHKOnly", String.valueOf(chk));
    }
    
    public Persistence getPersistence() {
        return Persistence.valueOf(_fields.get("Persistence"));
    }
    
    public void setPersistence(Persistence persistence) {
        _fields.put("Persistence", persistence.toString());
    }
    
    public String getClientToken() {
        return _fields.get("ClientToken");
    }
    
    /**
     * Sent back to client on the PersistentPut if this is a persistent request
     * @param clientToken 
     */
    public void setClientToken(String clientToken) {
        _fields.put("ClientToken", clientToken);
    }
    
    public boolean isGlobal() {
        return Boolean.parseBoolean(_fields.get("Global"));
    }
    
    /**
     * Whether the insert is visible on the global queue or not.
     * @param global 
     */
    public void setGlobal(boolean global) {
        _fields.put("Global", String.valueOf(global));
    }
    
    public boolean isDontCompress() {
        return Boolean.parseBoolean(_fields.get("DontCompress"));
    }
    
    /**
     * Hint to node: don't try to compress the data, it's already compressed
     * @param dontCompress 
     */
    public void setDontCompress(boolean dontCompress) {
        _fields.put("DontCompress", String.valueOf(dontCompress));
    }

    public String getFileName() {
        return _fields.get("FileName");
    }
    
    public void setFileName(String fileName) {
        _fields.put("FileName", fileName);
    }
    
    public String getDefaultName() {
        return _fields.get("DefaultName");
    }
    
    public void setDefaultName(String defaultName) {
        _fields.put("DefaultName", defaultName);
    }
    

    public void setAllowUnreadableFiles(boolean b) {
        _fields.put("AllowUnreadableFiles", String.valueOf(b));
    }
    
    public boolean isAllowUnreadableFiles() {
        return Boolean.parseBoolean(_fields.get("AllowUnreadableFiles"));
    }
}
