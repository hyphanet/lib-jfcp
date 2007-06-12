/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.message.node;

/**
 *
 * @author res
 */
public interface DataHoldingMessage {
    public String getData();
    public void setData(String data);
    public int getDataLength();
}
