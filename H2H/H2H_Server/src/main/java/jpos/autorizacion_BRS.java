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
import jpos.ServerBRS;

/**
 *
 * @author wellington.perez
 */
public class autorizacion_BRS {

    /**
     * @param args the command line arguments
     */
   public boolean process (ISOSource source, ISOMsg msg) {
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
                           msg.set(54, "78456");
                           msg.set(59, "123");
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                       
                         //////////////Retiro///////////////////////
                        else if(msg.getString(3).equals("011000")){
                          msg.set(38,"123456");
                           msg.set(59, "123");
                           msg.set(44,"0000000000000000000000000");
                           msg.set(54,"000000000000");
                           msg.set(102,"5620765570");
                           
                           }
                        
                          //////////////Retiro con token///////////////////////
                        else if(msg.getString(3).equals("420000")){
                          
                           msg.set(37,"004443");
                           msg.set(38,"123456");
                           msg.set(39,"123456");
                           msg.set(59, "123");
                          msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                          //////////////Pago de Cuota Credimas en efectivo:///////////////////////
                        else if(msg.getString(3).equals("520000")){
                          
                           msg.set(38,"123456");
                           msg.set(39,"123789");
                           msg.set(59, "125463");
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                             //////////////Pago de Tarjeta de Crédito en efectivo:///////////////////////
                        else if(msg.getString(3).equals("500000")){
                          
                           msg.set(38,"123456");
                           msg.set(39,"123789");
                           msg.set(59, "125463");
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                              //////////////Transferencia entre cuenta///////////////////////
                        else if(msg.getString(3).equals("402020")){
                           msg.set(37,"3432");   
                          msg.set(38,"1303400");
                           msg.set(39,"130400");
                           msg.set(58,"4545");
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                            //////////////Pago poliza de seguro///////////////////////
                        else if(msg.getString(3).equals("130000")){
                           msg.set(37,"3432");   
                           msg.set(38,"1303400");
                           msg.set(39,"130400");
                           msg.set(58,"4545");
                           msg.set(60,"ok");
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                           //////////////Pago pgr///////////////////////
                        else if(msg.getString(3).equals("130000")){
                           msg.set(37,"3432");   
                           msg.set(38,"1303400");
                           msg.set(39,"130400");
                           msg.set(58,"4545");
                           
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                             //////////////Pago impuesto///////////////////////
                        else if(msg.getString(3).equals("160000")){
                           msg.set(37,"3432");   
                           msg.set(38,"1303400");
                           msg.set(39,"130400");
                           msg.set(59,"4545");
                           
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                            //////////////Pago Suplidor///////////////////////
                        else if(msg.getString(3).equals("290000")){
                           msg.set(37,"3432");   
                           msg.set(38,"1303400");
                           msg.set(39,"130400");
                           msg.set(58,"4545");
                           
                           
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                             /////////////////////////Pago Multientidad//////
                         
                         if(msg.getString(3).equals("290000")){
                           msg.set(38,"1303400");
                           msg.set(39,"130400");
                           msg.set(59,"4545");
			   //msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                         
                               /////////////////////////Pago Remesa//////
                         
                         if(msg.getString(3).equals("440000")){
                           msg.set(38,"1303400");
                           msg.set(39,"130400");
                           msg.set(58,"4545");
                           msg.set(62,"00");
			   //msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                        
                        
                        
                        
                         
                         else if(msg.getString(3).equals("290000")){
                           msg.set(37,"130400");
                           msg.set(38, "123456724");
                           msg.set(58, "48458475893");
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                        
                           }
                         /////// pago de servicios PSP EF////////
                            else if(msg.getString(3).equals("150000")){
                           msg.set(37,"776554");
                           msg.set(38,"130400");
                           msg.set(39,"130400");
                           msg.set(58,"00000");
                           msg.set(59,"0032000");
			   //msg.set(61,"339& 0000200339! QI00317 00000000000000000000 001116341350000 BELKIS A GONZALEZ ROBINSON GONZALEZ 0000037409000012923002 15258545468");                         
                           msg.unset(42);
                        
                           msg.unset(22);
                           
                           }
                         
                            /////// pago de servicios PSP TC////////
                            else if(msg.getString(3).equals("15A000")){
                           msg.set(37,"776554");
                           msg.set(38,"130400");
                           msg.set(39,"130400");
                           msg.set(58,"00000");
                           msg.set(59,"0032000");
			   //msg.set(61,"339& 0000200339! QI00317 00000000000000000000 001116341350000 BELKIS A GONZALEZ ROBINSON GONZALEZ 0000037409000012923002 15258545468");                         
                           msg.unset(42);
                        
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
