/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenetproject.contrib.fcp.message.client;

import org.freenetproject.contrib.fcp.event.support.FcpEventSupportRepository;
import org.freenetproject.contrib.fcp.message.Persistence;
import org.freenetproject.contrib.fcp.message.UploadFrom;

/**
 * <p>This is used to specify an insert into Freenet of a single file. 
 * The insert may be provided by referring to a file on disk, including the data directly, 
 * or redirecting to another key.</p>
 * <p>A filename may be specified using the TargetFilename option. This is mostly useful 
 * with CHKs. The effect is to create a single file manifest which contains only the filename 
 * given, and points to the data just inserted. Thus the provided filename becomes the last 
 * part of the URI, and must be provided when fetching the data.</p>
 *
 * @author Ralph Smithen
 */
public class ClientPut extends ClientMessage{
    private static String[] _mandatoryFields;
       
    
    /**
     * Creates a ClientPut for direct data sending.
     * @param uri 
     * @param id 
     * @param data 
     */
    public ClientPut(String uri, String id, byte[] data){
        setUri(uri);
        setId(id);
        setData(data);
        setUploadFrom(UploadFrom.direct);
    }
    
    /**
     * {@inheritDoc}
     */
    public String[] getMandatoryFields() {
        return _mandatoryFields;
    }
    
    /**
     * {@inheritDoc}
     */
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
     *   0: report when complete, 
     *   1: SimpleProgress messages,
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
    
    public boolean isBlob() {
        return Boolean.parseBoolean(_fields.get("BinaryBlob"));
    }
    
    public void setBlob(boolean blob) {
        _fields.put("BinaryBlob", String.valueOf(blob));
    }
    
    public boolean isEarlyEncode() {
        return Boolean.parseBoolean(_fields.get("EarlyEncode"));
    }
    
    public void setEarlyEncode(boolean earlyEncode) {
        _fields.put("EarlyEncode", String.valueOf(earlyEncode));
    }
    
    public UploadFrom getUploadFrom() {
        return UploadFrom.valueOf(_fields.get("UploadFrom"));
    }
    
    public void setUploadFrom(UploadFrom uploadFrom) {
        _fields.put("UploadFrom", uploadFrom.toString());
        switch(uploadFrom){
            case direct:
                _mandatoryFields = new String[] {"URI", "Identifier", "DataLength"};
                break;
                
            case disk: 
                _mandatoryFields = new String[] {"URI", "Identifier", "Filename"};
                break;
                
            case redirect:
                _mandatoryFields = new String[] {"URI", "Identifier", "TargetURI"};
        }
    }

    public String getFileName() {
        return _fields.get("FileName");
    }
    
    public void setFileName(String fileName) {
        _fields.put("FileName", fileName);
    }
    
    public String getTargetFilename() {
        return _fields.get("TargetFilename");
    }
    
    public void setTargetFilename(String targetFilename) {
        _fields.put("TargetFilename", targetFilename);
    }
    
    public String getTargetURI() {
        return _fields.get("TargetURI");
    }
    
    public void setTargetURI(String targetURI) {
        _fields.put("TargetURI", targetURI);
    }
}
