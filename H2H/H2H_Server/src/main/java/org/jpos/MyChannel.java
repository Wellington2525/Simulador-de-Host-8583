package org.jpos;

import org.jpos.iso.*;
import org.jpos.util.LogEvent;
import org.jpos.util.Logger;

import java.io.IOException;
import java.net.ServerSocket;

public class MyChannel extends BaseChannel {
    /**
     * Public constructor 
     */
    public MyChannel () {
        super();
    }
    /**
     * Construct client ISOChannel
     * @param host  server TCP Address
     * @param port  server port number
     * @param p     an ISOPackager
     * @param TPDU  an optional raw header (i.e. TPDU)
     * @see ISOPackager
     */
    public MyChannel (String host, int port, ISOPackager p, byte[] TPDU) {
        super(host, port, p);
        this.header = TPDU;
    }
    /**
     * Construct server ISOChannel
     * @param p     an ISOPackager
     * @param TPDU  an optional raw header (i.e. TPDU)
     * @exception IOException
     * @see ISOPackager
     */
    public MyChannel (ISOPackager p, byte[] TPDU) throws IOException {
        super(p);
        this.header = TPDU;
    }
    /**
     * constructs server ISOChannel associated with a Server Socket
     * @param p     an ISOPackager
     * @param TPDU  an optional raw header (i.e. TPDU)
     * @param serverSocket where to accept a connection
     * @exception IOException
     * @see ISOPackager
     */
    public MyChannel (ISOPackager p, byte[] TPDU, ServerSocket serverSocket) 
        throws IOException
    {
        super(p, serverSocket);
        this.header = TPDU;
    }
    protected void sendMessageLength(int len) throws IOException {
        try {
            serverOut.write (                                                                                                         
                ISOUtil.str2bcd (                                                                                                     
                    ISOUtil.zeropad (Integer.toString (len), 4), true
                )
            );
        } 
        catch (ISOException e) {
            Logger.log (new LogEvent (this, "send-message-length", e));
        }
    }
    protected int getMessageLength() throws IOException, ISOException {
    	byte[] b = new byte[2];
        serverIn.readFully(b,0,2);
        return Integer.parseInt (ISOUtil.bcd2str (b, 0, 4, true),16);
    }
    protected void sendMessageHeader(ISOMsg m, int len) throws IOException { 
        byte[] h = m.getHeader();
        if (h != null) {
            if (h.length == 5) {
                // swap src/dest address
                byte[] tmp = new byte[2];
                System.arraycopy (h,   1, tmp, 0, 2);
                System.arraycopy (h,   3,   h, 1, 2);
                System.arraycopy (tmp, 0,   h, 3, 2);
            }
        }
        else
            h = header ;
        if (h != null) 
            serverOut.write(h);
    }
    /**
     * New QSP compatible signature (see QSP's ConfigChannel)
     * @param header String as seen by QSP
     */
    public void setHeader (String header) {
        super.setHeader (ISOUtil.str2bcd(header, false));
    }
}

