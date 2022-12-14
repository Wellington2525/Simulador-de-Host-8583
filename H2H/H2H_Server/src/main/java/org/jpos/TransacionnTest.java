/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpos;

import java.io.IOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jpos.IsoServer;
import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOFilter;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;

/**
 *
 * @author wellington.perez
 */
public class  TransacionnTest implements ISORequestListener  {

    /**
     * @param args the command line arguments
     */
   private static ISOPackager _PACKAGER;
	private static ServerChannel _CHANNEL;
	private static String _HOST_NAME = "127.0.0.1";
	private static int _SERVER_PORT = 10012;
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_CYAN = "\u001B[36m";
    
	public static void main(String[] args) {
        
		/* tipe paket yang dikirimkan 
		banyak paket yang bisa digunakan seperti BASE24,FSD,ISO87B, dsb..
		bisa dilihat di https://github.com/jpos/jPOS/tree/master/jpos/src/main/java/org/jpos/iso/packager */
		_PACKAGER = new ISO87APackager();
        
        /* generate channel menggunakan tipe packager diatas
		begitu juga dengan channel ada banyak tipe juga, silahkan lihat di 
		https://github.com/jpos/jPOS/tree/master/jpos/src/main/java/org/jpos/iso/channel 
		kita menggunakan ASCII agar bisa mengirim pesan teks CMIIW */
		_CHANNEL = new ASCIIChannel(_HOST_NAME, _SERVER_PORT, _PACKAGER);

        /* generate wrapper server jpos */
        ISOServer server = new ISOServer(_SERVER_PORT, _CHANNEL, null);
        
        /* berarti bisa jalanin banyak server sekaligus.. mantap juga */
        server.addISORequestListener(new IsoServer());

        /* pakai threading agar jalan di background process.. good */
        new Thread(server).start();
 
    	System.out.println(ANSI_GREEN+"Server [ISO8583] Ready On "+_HOST_NAME+":" + _SERVER_PORT+ANSI_RESET);
	}

	public boolean process(ISOSource isoSrc, ISOMsg isoMsg) {
		try {
			
			String bitmap = new String(isoMsg.pack());
			
			System.out.print("\n");
			System.out.println(ANSI_CYAN+"Server Receiving Connection From ["+((BaseChannel)isoSrc).getSocket().getInetAddress().getHostAddress()+"]"+ANSI_RESET);
			System.out.println("RAW      : " + bitmap);
			System.out.println("MTI      : " +ANSI_BLUE+ isoMsg.getMTI()+ANSI_RESET);
			System.out.println("BITMAP 1 : " + bitmap.substring(4,20));

			int dataFirstIndex = 21;
			if(!(Character.getNumericValue(bitmap.substring(4,20).charAt(0)) < 8)){
				System.out.println("BITMAP 2 : " + bitmap.substring(21,37));
				dataFirstIndex = 38;
				if(!(Character.getNumericValue(bitmap.substring(21,37).charAt(0)) < 8)){
					System.out.println("BITMAP 3 : " + bitmap.substring(38,54));
					dataFirstIndex = 55;
				}
			}
			System.out.println("DATA     : " + bitmap.substring(dataFirstIndex));
			System.out.println("XML      : "+ANSI_BLUE);
			isoMsg.dump(System.out, "");
			System.out.println(ANSI_RESET);
			
			/* jika 1800 berarti hanya echo test (sesuai standar) */
			if (isoMsg.getMTI().equalsIgnoreCase("1800")) {
				ProcNetworkManagement(isoSrc, isoMsg);
			}
			if (isoMsg.getMTI().equalsIgnoreCase("0800")) {
				ProcTransaction(isoSrc, isoMsg);
			}
			
		} catch (IOException ex) {
			Logger.getLogger(IsoServer.class.getName()).log(Level.ERROR, null, ex);
		} catch (ISOFilter.VetoException ex) {
			Logger.getLogger(IsoServer.class.getName()).log(Level.ERROR, null, ex);
		} catch (ISOException ex) {
			Logger.getLogger(IsoServer.class.getName()).log(Level.ERROR, null, ex);
		}
		return false;
	}
	
	private void ProcNetworkManagement(ISOSource isoSrc, ISOMsg isoMsg) throws ISOException, IOException {
        System.out.println("MOD      : ECHO TEST ");
        
        /* Balas respon dengan data yang sama cuma bebera field diubah */
        ISOMsg reply = (ISOMsg) isoMsg.clone();
        reply.setMTI("1810");
        //reply.setResponseMTI(); // balas MTI\
        isoSrc.send(reply);
	}
	
	private void ProcTransaction(ISOSource isoSrc, ISOMsg isoMsg) throws ISOException, IOException {
        System.out.println("MOD      : TRANSACTION REQUEST ");
        ISOMsg reply = (ISOMsg) isoMsg.clone();
        reply.setMTI("0810");
        reply.set(39, "00"); // respon berhasil
        reply.set(88, "9850000");
        isoSrc.send(reply);
	}
    
}
