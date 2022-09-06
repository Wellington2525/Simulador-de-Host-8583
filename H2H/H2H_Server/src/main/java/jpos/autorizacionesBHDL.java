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

/**
 *
 * @author wellington.perez
 */
public class autorizacionesBHDL {

    /**
     * @param args the command line arguments
     */
        
   public boolean process (ISOSource source, ISOMsg msg) {
             try {
               
			serverBHDL L = new serverBHDL();	    
		         L.logISOMsg(msg);
                        
		
			msg.setResponseMTI ();
			msg.set(39,"00");
                  
			if(msg.hasField(3)){
                                
                          
                   /////////// Autorizaciones /////////////// 

                          //////Recarga//////////
                         if(msg.getString(3).equals("380000")){
                           msg.set(38,"123456");
			   //msg.set(54,"000000000000");
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
                           msg.set(59,"00000");  
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
    
}
