/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message;

/**
 *Interface for manipulating a message with a data payload.
 *
 * @author Ralph Smithen
 */
public interface DataHoldingMessage {
    public byte[] getData();
    public void setData(byte[] data);
    public int getDataLength();
}
