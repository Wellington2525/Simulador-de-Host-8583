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
public class LogRecarga {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        ISOChannel channel = new ASCIIChannel(
                "192.168.5.60",2004, new ISO87APackager()
        
        );
        ((LogSource)channel).setLogger(logger, "Test-channel");
        channel.connect();
        
        ISOMsg m = new ISOMsg();
       // m.setHeader("ISO011000017".getBytes());
        m.setMTI("0200");
        m.set(1,"0000000010000001");
        m.set(3,"380000");
        m.set(4,"000000010000");
        m.set(7, ISODate.getDateTime(new Date()));
        m.set(11, "003295");
        m.set(12,"151152");
        m.set(17,"0901");
        m.set(18,"1");
        m.set(22,"010");
        m.set(32,"10000000024");
        m.set(35,"970000=4912101");
        m.set(37,"244376703295");
        m.set(41, "00053767");
        m.set(42,"");
        m.set(43,"COMERCIAL");
        m.set(48,"0519003866^0025^0001");
        m.set(49,"214");
        m.set(52,"00404004");
        m.set(60,"0024^ 0519^+000^0000");
        m.set(61,"0024"); 
        m.set(62,"NIITID");
        m.set(62,"&^  ^ 00002^ 00056^ !^  ^ P0^ 00032^  ^ 1003");
        m.set(100,"10000000000");
        //m.set(128,"000000");

        
        channel.send (m);
        ISOMsg r = channel.receive ();
        channel.disconnect (); 
       
    
    
    }
    
}
