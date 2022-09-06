/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpos;

import java.io.IOException;
import java.net.ServerSocket;
import static org.aspectj.bridge.MessageUtil.fail;
import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;
import org.jpos.util.LogEvent;
import org.jpos.util.Logger;

/**
 *
 * @author wellington.perez
 */
public class MyChannel2 extends BaseChannel {

   public MyChannel2 () {
        super();
    }
    /**
     * Construct client ISOChannel
     * @param host  server TCP Address
     * @param port  server port number
     * @param p     an ISOPackager
     * @see ISOPackager
     */
    public MyChannel2 (String host, int port, ISOPackager p) {
        super(host, port, p);
    }
    /**
     * Construct server ISOChannel
     * @param p     an ISOPackager
     * @see ISOPackager
     * @exception IOException
     */
    public MyChannel2 (ISOPackager p) throws IOException {
        super(p);
    }
    /**
     * constructs a server ISOChannel associated with a Server Socket
     * @param p     an ISOPackager
     * @param serverSocket where to accept a connection
     * @exception IOException
     * @see ISOPackager
     */
    public MyChannel2 (ISOPackager p, ServerSocket serverSocket) 
        throws IOException
    {
        super(p, serverSocket);
    }
    /**
     * @param m the Message to send (in this case it is unused)
     * @param len   message len (ignored)
     * @exception IOException
     */
    protected void sendMessageTrailler(ISOMsg m, int len) throws IOException {
        serverOut.write (3);
    }
    protected void sendMessageLength(int len) throws IOException {
        len++;  // one byte trailler
        serverOut.write (len >> 8);
        serverOut.write (len);
    }
    protected int getMessageLength() throws IOException, ISOException {
        
       byte[] b = new byte[2];
        serverIn.readFully(b,0,2);
        return Integer.parseInt (ISOUtil.bcd2str (b, 0, 4, true),16);
        
        
    }
   /*@Override
    protected void getMessageTrailler() throws IOException {
        Logger.log (new LogEvent (this, "get-message-trailler"));
        byte[] b = new byte[2];
        serverIn.readFully(b,0,2);
        Logger.log (new LogEvent (this, "got-message-trailler", ISOUtil.hexString(b)));
    }*/
    
    
    
}
