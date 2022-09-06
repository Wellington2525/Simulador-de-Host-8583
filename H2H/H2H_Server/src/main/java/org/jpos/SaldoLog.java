/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpos;

import java.util.Date;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISODate;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

/**
 *
 * @author wellington.perez
 */
public class SaldoLog {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        //ISOChannel channel = new ASCIIChannel("192.168.5.60",2004, new ISO87APackager()
        ISOChannel channel = new ASCIIChannel("127.0.0.1",10012, new ISO87APackager()        
        
        );
        ((LogSource)channel).setLogger(logger, "Test-channel");
        channel.connect();
        
        ISOMsg m = new ISOMsg();
       // m.setHeader("ISO011000017".getBytes());
        m.setMTI("0200");
        m.set(1,"0000000010000001");
        m.set(3,"31100");
        m.set(4,"0000100");
        m.set(7, ISODate.getDateTime(new Date()));
        m.set(11, "000114");
        m.set(12,"120701");
        m.set(13,"0610");
        m.set(17,"0610");
        m.set(22,"021");
        m.set(32,"10000000024");
        m.set(35,"970000=4912101");
        m.set(37,"161062700114");
        m.set(41, "00000626");
        m.set(42,"");
        m.set(43,"Miriato II");
        m.set(48,"0501000085^0025^0001");
        m.set(49,"214");
        m.set(52,"00404004");
        m.set(60,"0024^ 0519^+000^0000");
        m.set(61,"0024"); 
        m.set(62,"NIITID");
        m.set(100,"10000000000");
        //m.set(128,"000000");

        
        channel.send (m);
        ISOMsg r = channel.receive ();
        channel.disconnect (); 
       
    
    
    }
    
}
