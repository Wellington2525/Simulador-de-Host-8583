/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpos;

import java.io.IOException;
import java.util.Date;
import org.jpos.iso.ISODate;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.XMLChannel;
import org.jpos.iso.packager.XMLPackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

/**
 *
 * @author wellington.perez
 */
public class Test2 {
    
    public Test2 () {
     super();
}
    public boolean process (ISOSource source, ISOMsg m) {
    try {
        m.setResponseMTI ();
        m.set (39, "00");
        source.send (m);
        } catch (ISOException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        return true;
        }
    
   public static void main (String[] args) throws Exception{
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        ServerChannel channel = new XMLChannel (new XMLPackager());
        ((LogSource)channel).setLogger(logger, "channel");
        ISOServer server = new ISOServer (2004, channel, null);
        server.setLogger(logger, "server");
        server.addISORequestListener(new Test ());
        new Thread(server).start();
       


}
    
}
