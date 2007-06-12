/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */

package org.freenet.contrib.fcp.listener;

import org.freenet.contrib.fcp.event.AllDataEvent;
import org.freenet.contrib.fcp.event.DataFoundEvent;
import org.freenet.contrib.fcp.event.FcpKeyRequestedEvent;
import org.freenet.contrib.fcp.event.FcpSimpleProgressEvent;
import org.freenet.contrib.fcp.event.GetFailedEvent;

/**
 *
 * @author res
 */
public interface FcpQueueListener {
    public void simpleProgressUpdate(FcpSimpleProgressEvent e);
    public void keyRequested(FcpKeyRequestedEvent e);
    public void dataFound(DataFoundEvent e);
    public void allData(AllDataEvent e);
    public void getFailed(GetFailedEvent e);
}
