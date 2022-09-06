/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpos;

import java.io.IOException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import jpos.serverBHDL;

/**
 *
 * @author wellington.perez
 */
public class msg_BHDL {

    /**
     * @param source
     * @param msg
     * @param args the command line arguments
     * @return 
     */
   public boolean process (ISOSource source, ISOMsg msg) {
             try {
               
			serverBHDL L = new serverBHDL();	    
		         serverBHDL.logISOMsg(msg);
                        
		
			msg.setResponseMTI ();
			msg.set(39,"00");
                  
			if(msg.hasField(3)){
          /////////////////////////Consultas//010/////////////////////////////////////////// 
                        
                           /////Consulta PRESTAMOS ///////////// 
                          if(msg.getString(3).equals("270000")){
                           msg.set(38,"MSGOK1");
                           msg.set(59, "00000");  
                           msg.set(54,"000000000000");
                           msg.set(102,"23207658402");
                           msg.set(121,"Juan Perez");		   
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                       
                         //////////////Consulta de Remesas///////////////////////
                        else if(msg.getString(3).equals("430000")){
                           msg.set(4,"000000270000");
                           msg.set(38,"130400");
                           
                           msg.set(59,"00000");
			   msg.set(63,"339& 0000200339! QI00317 00000000000000000000 001116341350000 BELKIS A GONZALEZ ROBINSON GONZALEZ 0000037409000012923002 15258545468");                         
                           msg.unset(42);
                        
                           msg.unset(22);
                           
                           }
                        /////////////CONSULTA DE DEPOSITO///////
                        else if(msg.getString(3).equals("190000")){
                          // msg.set(4,"000000270000");
                           msg.set(38,"130400");
                           msg.set(59,"4545");
			   msg.set(121,"Juanita Mendez");                          
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           /*msg.set(38,"MSGOK1");
                           msg.set(59, "00000");  
                           msg.set(54,"000000000000");
                           msg.set(102,"23207658402");
                           msg.set(121,"Juan Perez");		   
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);*/
                           
                           }
                        
                            //// Consulta Pin Pesos /////
                       else if(msg.getString(3).equals("020000")){
                           //msg.set(39,"00");
                           msg.set(4,"000000270000");
			   msg.set(35,"95000101284110010000000000=4912101");
                           msg.set(38,"130400");
                           msg.set(59,"4545");
			   msg.set(121,"Juan Perez");
                           msg.unset(42);
                           msg.unset(22);
             
                           }
                                       
                          ////////// Consulta de Pagos de servicios publicos
                        else if(msg.getString(3).equals("360000")){
                           //msg.set(39,"00");
                           msg.set(38,"130400");
                           msg.set(59,"4545");
			   msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
              
                           }
                   /////////// Autorizaciones /////////////// 

                          //////Recarga//////////
                       /*  if(msg.getString(3).equals("380000")){
                           msg.set(38,"123456");
			               msg.set(54,"000000000000");
                           msg.set(59, "123");
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                           ///////////Retiro con tarjeta///////////////
                           else if(msg.getString(3).equals("011000")){
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
                         else if(msg.getString(3).equals("281000")){
                           msg.set(38,"130400");
                           msg.set(59, "123456724");
                           msg.set(54,"000000000000");
                           msg.set(102,"23207658402");
                           msg.set(121,"Juan Perez");
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);   
                           
                           }
                         //////////////Pago de Remesas ///////////////////////
                         else if(msg.getString(3).equals("440000")){
                           msg.set(38,"130400");
                           msg.set(59, "123456724");              
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         ///////////DEPOSITO/////////////////
                         else if(msg.getString(3).equals("211000")){
                          msg.set(38,"MSGOK1");
                           msg.set(59, "00000");  
                           msg.set(54,"000000000000");
                           msg.set(102,"23207658402");
                           msg.set(121,"Juan Perez");	  
			                 
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                     /////////Pago pin peso///////////////////////////    
                         else if(msg.getString(3).equals("050000")){
                           msg.set(38,"130400");
                           msg.set(59, "123456724");
                           msg.set(121, "Juan Perez");                                                 
                           msg.unset(42);
                           msg.unset(22);
               
                           }
                         
                          //// pagos de servicios publicos
                         else if(msg.getString(3).equals("150000")){
                           msg.set(38,"130400");
                           msg.set(59, "123456724");
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                         }*/
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
}
