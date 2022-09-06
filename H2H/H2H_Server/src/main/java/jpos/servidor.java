package jpos;
import java.io.*;
import org.jpos.iso.*;
import org.jpos.util.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.channel.BASE24TCPChannel;
import org.jpos.iso.packager.*;
import org.jpos.iso.packager.BASE24Packager;
import jpos.MyChannel2;
import jpos.MyChannel;



public class servidor implements ISORequestListener{

	public servidor () {
		super();
	}
	
        @Override
	
	
	public boolean process (ISOSource source, ISOMsg msg) {
	     servidor sv = new  servidor();
            try {
               
				    
		         logISOMsg(msg);
		
			msg.setResponseMTI ();
			msg.set(39,"00");

                          //////Recarga//////////
                         if(msg.getValue(3).equals("380000")){
                           //msg.set(39,"00");
                           msg.set(38,"123456");
						   msg.set(54,"000000000000");
                           msg.set(59, "123");
                            msg.unset(42);
                            msg.unset(43);
                            msg.unset(22);
                           }
                           ///////////Retiro con tarjeta///////////////
                           else if(msg.getValue(3).equals("011000")){
                           //msg.set(39,"00");
                           msg.set(38,"123456");
                           msg.set(59, "123");
                           msg.set(44,"0000000002000000000020000");
                           msg.set(54,"000000000000");
                           msg.set(102,"23207658402");
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(52);
                           msg.unset(22);
                           msg.unset(23);
                           
                           }
                         
                          /////////////PAGO DE PRESTAMO//////////////
                          
                         else if(msg.getValue(3).equals("281000")){
                           //msg.set(39,"00");
                           //msg.set(4,"000000270000");
                           msg.set(38,"130400");
                           msg.set(59, "123456724");
                           
                           msg.set(54,"000000000000");
                           msg.set(102,"23207658402");
                           msg.set(121,"Juan Perez");
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                                      
                           }
                         //////////////Pago de Rmesa///////////////////////
                         else if(msg.getValue(3).equals("440000")){
                           //msg.set(39,"00");
                           //msg.set(4,"000000270000");
                           msg.set(38,"130400");
                           msg.set(59, "123456724");
                                                  
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                     /////////Pago pin peso///////////////////////////    
                         else if(msg.getValue(3).equals("050000")){
                           //msg.set(39,"00");
                           //msg.set(4,"000000270000");
                           msg.set(38,"130400");
                           msg.set(59, "123456724");
                           msg.set(121, "Juan Perez");
                                                                            
                           msg.unset(42);
                           msg.unset(22);
                                        
                           }
                         
                            //// pagos de servicios publicos
                         else if(msg.getValue(3).equals("150000")){
                           //msg.set(39,"00");
                           //msg.set(4,"000000270000");
                           msg.set(38,"130400");
                           msg.set(59, "123456724");
                                 
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
     //////////////////////
     /////////////////////////Consultas//010///////////////////////////////////////////                 
                                            
                /////Consulta PRESTAMO Y CONSULTA///////////// 
                        if(msg.getValue(3).equals("270000 ")){
                          // msg.set(39,"00");
                           //msg.set(4,"000000270000");
                           msg.set(38,"MSGOK1");
                           msg.set(59, "00000");
                           
                           msg.set(54,"000000000000");
                           msg.set(102,"23207658402");
                           msg.set(121,"Juan Perez");
						   
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                                     
                           }
                       
                         //////////////Consulta de Rmesa///////////////////////
                           else if(msg.getValue(3).equals("430000")){
                           //msg.set(39,"00");
                           msg.set(4,"000000270000");
                           msg.set(38,"130400");
                           msg.set(59,"00000");
		           msg.set(63,"339& 0000200339! QI00317 00000000000000000000 001116341350000 BELKIS A GONZALEZ ROBINSON GONZALEZ 0000037409000012923002 15258545468");
                                                   
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                                           
                           }
                        
                        else if(msg.getValue(3).equals("020000")){
                           //msg.set(39,"00");
                           msg.set(4,"000000270000");
	                   msg.set(35,"95000101284110010000000000=4912101");
                           msg.set(38,"130400");
                           msg.set(59,"4545");
						   msg.set(121,"Juan Perez");
                                                    
                           
                           msg.unset(42);
                           msg.unset(22);
                                           
                           }
                                       
                        
                        else if(msg.getValue(3).equals("360000")){
                           //msg.set(39,"00");
                           msg.set(38,"130400");
                           msg.set(59,"4545");
                           msg.set(61,"Energia Electrica 03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");
                                                      
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                                             
                           }
                           

                      
                      
                     
                     
                        /*if(msg.getMTI().equals("0810")){
                     
                           msg.set(39,"00");
                         
                           }    */            
                       
                        //msg.set(38,"123456");
                       // msg.set(44,"0000000000000000000000000");
                       // msg.set(54,"000000000000");
                       // msg.set(59, "123");
                        //msg.setResponseMTI();
                        //msg.unset("52");
                  
                                
                       
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
		ISOServer server = new ISOServer (8391, channel, null);
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
