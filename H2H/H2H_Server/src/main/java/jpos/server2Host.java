/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpos;

import java.io.IOException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ISOSource;
import org.jpos.iso.packager.BASE24Packager;

/**
 *
 * @author wellington.perez
 */
public class server2Host {
    
    public server2Host () {
		super();
	}

    /**
     * @param args the command line arguments
     */
    public boolean process (ISOSource source, ISOMsg msg) {
	     server2Host sv = new  server2Host();
            try {
               
				    
		         logISOMsg(msg);
		
			msg.setResponseMTI ();
			msg.set(39,"00");
                      if(msg.getMTI().equals("0210")){
                          //////Recarga//////////
                         if(msg.getValue(3).equals("380000")){
                          // msg.set(39,"00");
                           msg.set(38,"123456");
                           msg.set(59, "123");
                            msg.unset(42);
                            msg.unset(43);
                            msg.unset(22);
                           }
                           ///////////Retiro con tarjeta///////////////
                           
                         
                           
                           
                      }
                      //////////////////////
     /////////////////////////Consultas//010///////////////////////////////////////////                 
                      if(msg.getMTI().equals("0110")){
                                  
                /////Consulta PRESTAMO Y CONSULTA///////////// 
                        if(msg.getValue(3).equals("270000 ")){
                          // msg.set(39,"00");
                           //msg.set(4,"000000270000");
                           msg.set(38,"7765464");
                           msg.set(59, "00000000000000000000");
                           
                           msg.set(54,"000000000000");
                           msg.set(102,"23207658402");
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           msg.unset(23);
                           
                           }
                       
                         //////////////Consulta de Rmesa///////////////////////
                          
                           
                      }
                      
                      
                     
                     
                        if(msg.getMTI().equals("0810")){
                     
                           msg.set(39,"00");
                         
                           }                
                       
                
                        
                        //msg.set(38,"123456");
                       // msg.set(44,"0000000000000000000000000");
                       // msg.set(54,"000000000000");
                       // msg.set(59, "123");
                        //msg.setResponseMTI();
                        //msg.unset("52");
                        if(msg.getValue(52)!=null){
                            msg.unset("52");
                            
                        }
                                
                       
			source.send (msg);
                        sv.logISOMsg(msg);
		} catch (ISOException e) {
			e.printStackTrace();
		} 	catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException, ISOException{
		 //Logger logger = new Logger ();
		//logger.addListener (new SimpleLogListener ());
		 byte[] TPDU = new byte[12];
	     //ISOPackager p = new ISO87APackager();
		 BASE24Packager p = new BASE24Packager();
	     //BASE24TCPChannel channel = new BASE24TCPChannel (p);
              MyChannel2 channel = new MyChannel2 (p);
	     ((MyChannel2) channel).setHeader("ISO015000015");
		//((LogSource)channel).setLogger (logger, "channel");
		ISOServer server = new ISOServer (8093, channel, null);
		//server.setLogger (logger, "server");
		server.addISORequestListener (new servidor ());
		System.out.println("ISO8583 server started BHDL...");
		new Thread (server).start ();
		
		
			
	}
	
	private static void logISOMsg(ISOMsg msg) {
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
