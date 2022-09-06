/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpos;
import java.io.*;
import org.jpos.iso.*;
import org.jpos.util.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.channel.BASE24TCPChannel;
import org.jpos.iso.packager.*;
import org.jpos.iso.packager.BASE24Packager;
import jpos.MyChannel2;
import jpos.msg_BRS;
import jpos.autorizacion_BRS;

/**
 *
 * @author wellington.perez
 */
public class ServerBRS implements ISORequestListener {

   public ServerBRS () {
     super();
 }
	
   @Override
        public boolean process (ISOSource source, ISOMsg msg) {
	 
          ServerBRS BR = new ServerBRS();
            try {
               
			ServerBRS L = new ServerBRS();	    
		        L.logISOMsg(msg);
                        
		
			msg.setResponseMTI ();
			msg.set(39,"00");
                  
			if(msg.hasField(3)){
          /////////////////////////Autorizaciones/////////////////////////////////////////// 
                        
                           /////Recargas///////////// 
                        if(msg.getString(3).equals("380000")){
                           msg.set(38,"123456");
                           //msg.set(54, "78456");
                           msg.set(59, "123");
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                       
                         //////////////DEPOSITO///////////////////////
                         else if(msg.getString(3).equals("191000")){
                           msg.set(38,"1307");
                           msg.set(59,"4545");
			   msg.set(121,"Manuela Martez");                          
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                           
                          else  if(msg.getString(3).equals("212000")){
                           msg.set(38,"1304");
                           msg.set(59,"4545");
			   //msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                            
                            /////consulta saldo//////
                          else  if(msg.getString(3).equals("311000")){
                           //msg.set(37,"948634");
                            
                           msg.set(38,"123456");
                           
                           msg.set(59, "123");
                           msg.set(44,"0000000000000000000000000");
                           msg.set(54,"000000000000");
                           msg.set(102,"5620765570");
                           	   
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                            
                            /////Retiro TC//////
                        else if(msg.getString(3).equals("011000")){
                          msg.set(38,"12378");
                          //msg.set(44,"00098");
                          msg.set(54,"00450");
                          msg.set(59, "123");
                          msg.set(102,"5620765570");
                           
                           }
                             
                             //////////////cnsulta Retiro con token///////////////////////
                             
                        else  if(msg.getString(3).equals("370000")){
                           msg.set(4,"07");   
                           msg.set(38,"130400");
                           msg.set(58,"123456");
			   msg.set(63,"123456");                          
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                          //////////////Retiro con token///////////////////////
                             
                        else if(msg.getString(3).equals("420000")){
                           msg.set(4,"07");   
                           msg.set(38,"130400");
                           msg.set(58,"123456");
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                          
                             /////////////////////////Consultas tu credima//010//////
                         
                        else if(msg.getString(3).equals("510000")){
                          
                           msg.set(38,"130400");
                           //msg.set(39,"44545");
                           msg.set(59,"4545");
			   msg.set(121,"JOAQUIN ESPINOSA");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                            //////////////Pago de Cuota Credimas en efectivo:///////////////////////
                        else if(msg.getString(3).equals("520000")){
                           msg.set(38,"123456");
                           
                           msg.set(59, "125463");
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                                //////////////Pago de Cuota Credimas con TC:///////////////////////
                        else if(msg.getString(3).equals("521000")){
                           msg.set(38,"123456");
                           
                           msg.set(59, "125463");
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                                 //////////////Consulta  TC:///////////////////////
                        else if(msg.getString(3).equals("490000")){
                           msg.set(38,"123456");
                           
                           //msg.set(44,"123789");
                           msg.set(59, "125463");
                           msg.set(121,"JOAQUIN ESPINOSA");                          
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                                  //////////////Pago de  TC:///////////////////////
                        else if(msg.getString(3).equals("500000")){
                           msg.set(38,"123456");
                         
                           msg.set(59, "125463");
                           //msg.set(121,"JOAQUIN ESPINOSA");                          
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                                    //////////////300 onsulta Cuenta con Tarjeta

                        else if(msg.getString(3).equals("890000")){
                           
                           msg.set(61,"044121400000000123456789100=40222151504");                          
                                                                                
                           }
                        
                         
                                   //////////////Consulta trnasferencia:///////////////////////
                        else if(msg.getString(3).equals("392020")){
                           msg.set(38,"123456");
                           msg.set(59, "125463");
                           msg.set(121,"FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019");                          
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                          else if(msg.getString(3).equals("402020")){
                           msg.set(38,"123456");
                            msg.set(59, "125463");
                           //msg.set(121,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                            //////////////Consulta de Seguro///////////////////////
                          else if(msg.getString(3).equals("410000")){
                          msg.set(37,"948634");
                           msg.set(38,"123213");
                           msg.set(58, "00000");  
			   msg.set(61,"339& 0000200339! QI00317 00000000000000000000 001116341350000 BELKIS A GONZALEZ ROBINSON GONZALEZ 0000037409000012923002 15258545468");                         
                           msg.unset(42);
                        
                           msg.unset(22);
                           
                           }
                         
                          else if(msg.getString(3).equals("450000")){
                          msg.set(37,"948634");
                           msg.set(38,"123213");
                           
                           msg.set(58, "00000");  
			   msg.set(61,"339& 0000200339! QI00317 00000000000000000000 001116341350000 BELKIS A GONZALEZ ROBINSON GONZALEZ 0000037409000012923002 15258545468");                         
                           msg.unset(42);
                        
                           msg.unset(22);
                           
                           }
                         
                            /////////////////////////Consultas pago PGR//////
                         
                       else  if(msg.getString(3).equals("230000")){
                          msg.set(37,"3432");   
                          msg.set(38,"1303400");
                           
                           msg.set(58,"4545");
			   msg.set(61,"FERNANDEZ RODRIGUEZ, CARLITO VILLAN");
                            msg.set(63,"00");   
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                          else  if(msg.getString(3).equals("240000")){
                          msg.set(37,"3432");   
                          msg.set(38,"1303400");
                           
                           msg.set(58,"4545");
			   //msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                            
                        //////pago de impuesto////    
                      else  if(msg.getString(3).equals("350000")){
                          msg.set(37,"3432");   
                          msg.set(38,"130");
                           
                           msg.set(58,"4545");
			   msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                      else if(msg.getString(3).equals("160000")){
                          msg.set(37,"3432");   
                          msg.set(38,"1303");
                          
                           msg.set(58,"4545");
			   //msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                         /////////////////Pago de Suplidores/////////////////////
                          else   if(msg.getString(3).equals("260000")){
                          //msg.set(37,"3432");   
                          msg.set(38,"1303");
                           
                           msg.set(58,"4545");
			   msg.set(61,"FERNANDEZ RODRIGUEZ, CARLITO VILLAN");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                         else   if(msg.getString(3).equals("290000")){
                          //msg.set(37,"3432");   
                          msg.set(38,"130");
                           
                           msg.set(58,"458");
			   //msg.set(61,"FERNANDEZ RODRIGUEZ, CARLITO VILLAN");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                        
                         /////////////////Consulta Avon/////////////////////
                          else   if(msg.getString(3).equals("250000")){
                          msg.set(37,"3432");   
                          msg.set(38,"1303");
                          msg.set(58,"4545");
			   msg.set(61,"FERNANDEZ RODRIGUEZ, CARLITO VILLAN");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                         /////////////////Pago Avon/////////////////////
                          else   if(msg.getString(3).equals("290000")){
                          msg.set(37,"3432");   
                          msg.set(38,"1303");
                          msg.set(58,"4545");
			   //msg.set(61,"FERNANDEZ RODRIGUEZ, CARLITO VILLAN");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                        
                          /////////////////Consulta de Remesa/////////////////////
                          else   if(msg.getString(3).equals("410000")){
                          //msg.set(37,"3432");   
                          msg.set(38,"1303");
                          msg.set(58,"4545");
                          msg.set(59,"0001");
			  msg.set(63,"RIA, MONEY");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                           /////////////////    Consulta de Remesa/////////////////////
                          else   if(msg.getString(3).equals("430000")){
                          msg.set(4,"0006600");
                          msg.set(37,"3432");   
                          msg.set(59,"009");
                          msg.set(61,"jUANITA MARTINEZ");
                         // msg.set(59,"0001");
			  //msg.set(61,"FERNANDEZ RODRIGUEZ, CARLITO VILLAN");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                          /////////////////Pago de Remesa/////////////////////
                          else   if(msg.getString(3).equals("440000")){
                          //msg.set(4,"0006600");
                          msg.set(38,"3432");   
                          msg.set(59,"009");
                          msg.set(62,"0009");
                         // msg.set(59,"0001");
			  //msg.set(61,"FERNANDEZ RODRIGUEZ, CARLITO VILLAN");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                        
                         
                         
                        
			}       
                                
                       
			source.send (msg);
                        L.logISOMsg(msg);
		} catch (ISOException e) {
			e.printStackTrace();
		} 	catch (IOException e) {
			e.printStackTrace();
		}
             
             return true;
            
             
	}
       
	
	public static void main(String[] args) throws IOException, ISOException{
	    byte[] TPDU = new byte[12];
	     //ISOPackager p = new ISO87APackager();
		 BASE24Packager p = new BASE24Packager();
	     MyChannel2 channel = new MyChannel2 (p);
	     ((MyChannel2) channel).setHeader("ISO026000013");
	       ISOServer server = new ISOServer (8390, channel, null);
		
                server.addISORequestListener(new ServerBRS () );
		System.out.println("ISO8583 server started BRS...8390");
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
