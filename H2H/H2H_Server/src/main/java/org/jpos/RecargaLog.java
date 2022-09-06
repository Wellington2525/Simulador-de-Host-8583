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
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.channel.HEXChannel;
import org.jpos.iso.packager.ISO87APackager;

/**
 *
 * @author wellington.perez
 */
public class RecargaLog {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ISOException, IOException {
        ISOPackager pgk = new ISO87APackager();
        HEXChannel c = new HEXChannel();
        //ServerChannel channel = new Socket("127.0.0.1", 10012,pgk);
	ServerChannel channel = new ASCIIChannel("192.168.5.60",2004,pgk);
        //HEXChannel channel = new HEXChannel("192.168.5.60",8081,pgk);
        //BaseChannel bas = new BaseChannel("127.0.0.1",41514,pgk);
        channel.connect();
        ISOMsg m = new ISOMsg();
       //m.setHeader("ISO011000017".getBytes());
        m.setMTI("0200");
        m.set(1,"0000000010000001");
        m.set(2, "16");
        m.set(2, "5421287475388412");
        m.set(3,"000000");
        m.set(4,"400");
        m.set(7, "0716070815");
        m.set(11, "844515");
        m.set(12,"151152");
        m.set(13,ISODate.getDate(new Date()));
        m.set(17,"0901");
        m.set(18,"1");
        m.set(22,"010");
        m.set(32,"10000000024");
        m.set(35,"00");
        m.set(37,"244376703295");
        m.set(41, "00053767");
        m.set(42,"");
        m.set(43,"COMERCIAL");
        m.set(48,"0519003866         ^ 0025^ 0001 ");
        m.set(49,"214");
       
        channel.send(m);
        ISOMsg response = channel.receive();

        response.dump(System.out, "response:");
    }
    
}
