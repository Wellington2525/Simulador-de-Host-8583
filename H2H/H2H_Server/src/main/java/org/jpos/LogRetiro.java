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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wellington.perez
 */
public class LogRetiro {

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
      
 m.setMTI("0200");
 m.set(1,"0000000010000001");
 m.set(2,"16");
 m.set(3,"011000");
 m.set(4,"000000136000");
 m.set(7, ISODate.getDateTime(new Date()));
 m.set(11, "011381");
 m.set(12,"141557");
 //m.set(13,ISODate.getDate(new Date()));
 m.set(15,"1005");
 m.set(22,"051");
 m.set(23,"000");
 m.set(32,"123");
 m.set(35,"123342");
 m.set(41, "29110001");
 m.set(42,"111111111100001");
 m.set(43,"FARMACIA ABC CARRT");
 m.set(48,"DF01053132383032DF020A30323933333630313438DF090431313031DF0A06313233343536");
 m.set(49,"360");
 m.set(52,"00000000");
 m.set(55,"5E37F52");
 m.set(60,"0024");
 m.set(61,"0024"); 
 m.set(62,"97000000000000000000000000=4912101 ");
 m.set(70,"301");
 m.set(100,"10000000000");
 //m.set(128,"ABCDEF1234567890");
        //m.set(128,"000000");

        
        channel.send (m);
        ISOMsg r = channel.receive ();
        channel.disconnect (); 
       
    
    
    }
    
    
}
