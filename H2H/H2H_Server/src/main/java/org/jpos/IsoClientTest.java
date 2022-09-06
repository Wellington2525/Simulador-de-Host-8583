/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpos;

/**
 *
 * @author wellington.perez
 */
//import static com.sun.corba.se.impl.orbutil.ORBConstants.SOCKET;
import java.io.IOException;
import java.net.Socket;
import org.jpos.iso.channel.HEXChannel;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.channel.XMLChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.iso.packager.XMLPackager;
import org.jpos.iso.BaseChannel;



public class IsoClientTest {
    public static void main(String[] args) throws ISOException, IOException {
        ISOPackager pgk = new ISO87APackager();
        HEXChannel c = new HEXChannel();
        //ServerChannel channel = new ASCIIChannel("127.0.0.1", 10012,pgk);
	ServerChannel channel = new ASCIIChannel("192.168.5.60",2004,pgk);
        
        channel.connect();
        ISOMsg request = new ISOMsg();
        request.setMTI("0800");
        request.set(2, "16");
        request.set(2, "5421287475388412");
        request.set(3, "000000");
        request.set(4, "400.0");
        request.set(7, "0716070815");
        request.set(11, "844515");
        channel.send(request);
        ISOMsg response = channel.receive();

        response.dump(System.out, "response:");
    }
}
