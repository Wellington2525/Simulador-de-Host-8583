package jpos;

import org.jpos.iso.*;
import org.jpos.util.LogEvent;
import org.jpos.util.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.math.BigInteger;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;

public class MyChannel extends BaseChannel {

    /** Number of digits for the message length header */
    protected int lengthDigits= 4;                                      // 4 is default

    private static final BigInteger ten= BigInteger.valueOf(10L);     // just a static 10

    /**
     * Public constructor (used by Class.forName("...").newInstance())
     */
    public MyChannel () {
        super();
    }
    /**
     * Construct client ISOChannel
     * @param host  server TCP Address
     * @param port  server port number
     * @param p     an ISOPackager
     * @see ISOPackager
     */
    public MyChannel (String host, int port, ISOPackager p) {
        super(host, port, p);
    }
    /**
     * Construct server ISOChannel
     * @param p     an ISOPackager
     * @exception IOException
     * @see ISOPackager
     */
    public MyChannel (ISOPackager p) throws IOException {
        super(p);
    }
    /**
     * constructs a server ISOChannel associated with a Server Socket
     * @param p     an ISOPackager
     * @param serverSocket where to accept a connection
     * @exception IOException
     * @see ISOPackager
     */
    public MyChannel (ISOPackager p, ServerSocket serverSocket) 
        throws IOException
    {
        super(p, serverSocket);
    }


    public void setLengthDigits(int len) { lengthDigits= len; }
    public int getLengthDigits() { return lengthDigits; }


    /**
     * @param len the packed Message len
     * @exception IOException
     */
    protected void sendMessageLength(int len) throws IOException {
    	try {
           	String numStr = Integer.toHexString (len);
        	int num = Integer.parseInt(numStr);
        	
        	serverOut.write (                                                                                                         
                ISOUtil.str2bcd (                                                                                                     
                    ISOUtil.zeropad (Integer.toString (num), 4), true
                )
            );
        } 
        catch (ISOException e) {
            Logger.log (new LogEvent (this, "send-message-length", e));
        }
    }
    /**
     * @return the Message len
     * @exception IOException, ISOException
     */
    protected int getMessageLength() throws IOException, ISOException {
    	byte[] b = new byte[2];
        serverIn.readFully(b,0,2);
        return Integer.parseInt (ISOUtil.bcd2str (b, 0, 4, true),16);
    }


    /**
     *
     * Calls super.setConfiguration() and then reads the 'length-digits' property,
     * defaulting to 4
     *
     * @param cfg Configuration
     * @throws ConfigurationException
     */
    @Override
    public void setConfiguration (Configuration cfg) throws ConfigurationException
    {
        super.setConfiguration(cfg);
        setLengthDigits(cfg.getInt("length-digits", 4));
    }
    
   
}

