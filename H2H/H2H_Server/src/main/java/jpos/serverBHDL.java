package jpos;
import java.io.*;
import org.jpos.iso.*;
import org.jpos.util.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.channel.BASE24TCPChannel;
import org.jpos.iso.packager.*;
import org.jpos.iso.packager.BASE24Packager;
import jpos.MyChannel2;
import jpos.msg_BHDL;
import jpos.autorizacionesBHDL;



public class serverBHDL implements ISORequestListener{

	public serverBHDL () {
		super();
	}
	
        @Override
	public boolean process (ISOSource source, ISOMsg msg) {
            /////class validaciones MSG/////////////////////////
                msg_BHDL M = new msg_BHDL();
                autorizacionesBHDL B = new autorizacionesBHDL();
                
               
                
		return true;
	}
	
	public static void main(String[] args) throws IOException, ISOException{
    	 byte[] TPDU = new byte[12];
		 BASE24Packager p = new BASE24Packager();
          MyChannel2 channel = new MyChannel2 (p);
	     ((MyChannel2) channel).setHeader("ISO015000015");
		ISOServer server = new ISOServer (8391, channel, null);
		server.addISORequestListener (new servidorJohan ());
		System.out.println("ISO8583 server started BHDL...8391");
		new Thread (server).start ();
		
		
			
	}
	
	public static void logISOMsg(ISOMsg msg) {
		System.out.println("----ISO MESSAGE-----");
		try {
			System.out.println("  MTI : " + msg.getMTI());
			for (int i = 1; i <= msg.getMaxField(); i++) {
				if (msg.hasField(i)) {
					System.out.println("    Field-" + i + " : "
							+ msg.getString(i));
				}
			}
		} catch (ISOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("--------------------");
		}

	}

}
