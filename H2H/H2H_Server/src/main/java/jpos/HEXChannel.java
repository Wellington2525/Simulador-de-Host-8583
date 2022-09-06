/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpos;

/**
 *
 * @author wellington.perez
 */
import org.jpos.iso.*;
import org.jpos.util.LogEvent;
import org.jpos.util.Logger;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Sends a four ASCII hex characters indicating message length (up to 0xffff)
 *
 * @author Mladen Mrkic <mmrkic@arius.co.yu>
 * @author apr
 * @version $Revision$ $Date$
 * @see ISOMsg
 * @see ISOException
 * @see ISOChannel
 */
public class HEXChannel extends BaseChannel {
    public HEXChannel () {
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
    public HEXChannel (String host, int port, ISOPackager p, byte[] TPDU) {
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
    public HEXChannel (ISOPackager p, byte[] TPDU) throws IOException {
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
    public HEXChannel (ISOPackager p, byte[] TPDU, ServerSocket serverSocket) 
        throws IOException
    {
        super(p, serverSocket);
        this.header = TPDU;
    }
    @Override
    protected void sendMessageLength(int len) throws IOException {
        if (len > 0xFFFF)
            throw new IOException (len + " exceeds maximum length");
        try {
            serverOut.write (
                ISOUtil.zeropad (Integer.toString (len % 0xFFFF,16), 4).getBytes()
            );
        } 
        catch (ISOException e) {
            Logger.log (new LogEvent (this, "send-message-length", e));
        }
    }
    protected int getMessageLength() throws IOException, ISOException {
        byte[] b = new byte[4];
        serverIn.readFully(b,0,4);
        return Integer.parseInt (new String(b),16);
    }
}

