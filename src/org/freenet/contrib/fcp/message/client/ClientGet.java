/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.client;

import org.freenet.contrib.fcp.event.support.FcpEventSupportRepository;
import org.freenet.contrib.fcp.message.Persistence;
import org.freenet.contrib.fcp.message.ReturnType;

/**
 *This is sent from a client program to the Freenet node and is used to specify a file to download from Freenet.
 *
 * @author Ralph Smithen
 */
public class ClientGet extends ClientMessage{
    
    public ClientGet(String uri, String identifier){
        setUri(uri);
        setId(identifier);
    }
    
    public String[] getMandatoryFields() {
        return new String[] { "URI", "Identifier" };
    }

    protected void fireEvents(FcpEventSupportRepository eventSupport) {
        eventSupport.getQueueEventSupport().fireKeyRequested(this);
    }
    
    
    public boolean isIgnoreDs() {
        return Boolean.parseBoolean(_fields.get("IgnoreDS"));
    }
    
    public void setIgnoreDs(boolean ignoreDs) {
        _fields.put("IgnoreDS", String.valueOf(ignoreDs));
    }
    
    public boolean isDsOnly() {
        return Boolean.parseBoolean(_fields.get("DSonly"));
    }
    
    public void setDsOnly(boolean dsOnly) {
        _fields.put("DSonly", String.valueOf(dsOnly));
    }
    
    public String getUri() {
        return _fields.get("URI");
    }
    
    public void setUri(String uri) {
        _fields.put("URI", uri);
    }
    
    public String getId() {
        return _fields.get("Identifier");
    }
    
    public void setId(String id) {
        _fields.put("Identifier", id);
    }
    
    public int getVerbosity() {
        return Integer.parseInt(_fields.get("Verbosity"));
    }
    
    public void setVerbosity(int verbosity) {
        _fields.put("Verbosity", String.valueOf(verbosity));
    }
    
    public int getMaxSize() {
        return Integer.parseInt(_fields.get("MaxSize"));
    }
    
    public void setMaxSize(int maxSize) {
        _fields.put("MaxSize", String.valueOf(maxSize));
    }
    
    public int getMaxTempSize() {
        return Integer.parseInt(_fields.get("MaxTempSize"));
    }
    
    public void setMaxTempSize(int maxTempSize) {
        _fields.put("MaxTempSize", String.valueOf(maxTempSize));
    }
    
    public int getMaxRetries() {
        return Integer.parseInt(_fields.get("MaxRetries"));
    }
    
    public void setMaxRetries(int maxRetries) {
        _fields.put("MaxRetries", String.valueOf(maxRetries));
    }
    
    public int getPriority() {
        return Integer.parseInt(_fields.get("PriorityClass"));
    }
    
    public void setPriority(int priority) {
        _fields.put("PriorityClass", String.valueOf(priority));
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
    
    public void setClientToken(String clientToken) {
        _fields.put("ClientToken", clientToken);
    }
    
    public boolean isGlobal() {
        return Boolean.parseBoolean(_fields.get("Global"));
    }
    
    public void setGlobal(boolean global) {
        _fields.put("Global", String.valueOf(global));
    }
    
    public boolean isBlob() {
        return Boolean.parseBoolean(_fields.get("BinaryBlob"));
    }
    
    public void setBlob(boolean blob) {
        _fields.put("BinaryBlob", String.valueOf(blob));
    }
    
    public ReturnType getReturnType() {
        return ReturnType.valueOf(_fields.get("ReturnType"));
    }
    
    public void setReturnType(ReturnType returnType) {
        _fields.put("ReturnType", returnType.toString());
    }
    
    // Only valid if ReturnType is disk
    
    public String getFileName() {
        return _fields.get("FileName");
    }
    
    public void setFileName(String fileName) {
        _fields.put("FileName", fileName);
    }
    
    public String getTempFileName() {
        return _fields.get("TempFilename");
    }
    
    public void setTempFileName(String tempFileName) {
        _fields.put("TempFilename", tempFileName);
    }
}
