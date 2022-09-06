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
public class msg_BRS {

    /**
     * @param source
     * @param msg
     * @return 
     */
    public boolean process (ISOSource source, ISOMsg msg) {
             try {
               
			ServerBRS L = new ServerBRS();	    
		       ServerBRS.logISOMsg(msg);
                        
		
			msg.setResponseMTI ();
			msg.set(39,"00");
                  
			if(msg.hasField(3)){
          /////////////////////////Consultas//010/////////////////////////////////////////// 
           if(msg.getString(3).equals("311000")){
                           msg.set(37,"948634");
                           msg.set(38,"MSGOK1");
                           msg.set(58, "00000");  
                           msg.set(54,"000000000000");
                           msg.set(61,"Marta Heredia");
                           	   
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         /////////////////////////Consultas Deposito//010//////
                         
                         if(msg.getString(3).equals("192000")){
                         msg.set(38,"130400");
                           msg.set(59,"4545");
			   msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                            /////////////////////////Consultas tu efectivo//010//////
                         
                         if(msg.getString(3).equals("370000")){
                           msg.set(37,"004443");
                           msg.set(38,"130400");
                           msg.set(58,"4545");
			   msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                           /////////////////////////Consultas tu credima//010//////
                         
                         if(msg.getString(3).equals("510000")){
                          
                           msg.set(38,"130400");
                           msg.set(59,"4545");
			   msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                             /////////////////////////Consultas balance de tarjeta//010//////
                         
                         if(msg.getString(3).equals("490000")){
                          msg.set(39,"1303400");
                           msg.set(38,"130400");
                           msg.set(59,"4545");
			   msg.set(121,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                             /////////////////////////Consultas transferencia entre cuenta//////
                         
                         if(msg.getString(3).equals("392020")){
                          msg.set(37,"3432");   
                          msg.set(38,"1303400");
                           msg.set(39,"130400");
                           msg.set(58,"4545");
			   msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                             /////////////////////////Consultas pago de seguro//////
                         
                         if(msg.getString(3).equals("120000")){
                          msg.set(37,"3432");   
                          msg.set(38,"1303400");
                           msg.set(39,"130400");
                           msg.set(58,"4545");
			   msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                           /////////////////////////Consultas pago PGR//////
                         
                         if(msg.getString(3).equals("230000")){
                          msg.set(37,"3432");   
                          msg.set(38,"1303400");
                           msg.set(39,"130400");
                           msg.set(58,"4545");
			   msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                             /////////////////////////Consultas Impuesto//////
                         
                         if(msg.getString(3).equals("350000")){
                          msg.set(37,"3432");   
                          msg.set(38,"1303400");
                           msg.set(39,"130400");
                           msg.set(59,"4545");
			   msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                         
                              /////////////////////////Consultas Multientidad//////
                         
                         if(msg.getString(3).equals("260000")){
                          msg.set(37,"3432");   
                          msg.set(38,"1303400");
                           msg.set(39,"130400");
                           msg.set(58,"4545");
			   //msg.set(61,"Energia Electrica  03/10/2013000000000000000000000000000000000000000000000000FERNANDEZ RODRIGUEZ, CARLITO VILLAN            215600000001431L00953170019                   000000000000000CC");                          
                         
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                         
                         
                         
                         
                           /////Consulta avon ///////////// 
                         if(msg.getString(3).equals("250000 ")){
                           msg.set(37,"948634");
                             msg.set(38,"MSGOK1");
                           msg.set(58, "00000");  
                           msg.set(54,"000000000000");
                           msg.set(61,"Marta Heredia");
                           	   
                           msg.unset(42);
                           msg.unset(43);
                           msg.unset(22);
                           
                           }
                       
                         //////////////Consulta de Remesas///////////////////////
                          else if(msg.getString(3).equals("410000")){
                          msg.set(37,"948634");
                           msg.set(38,"123213");
                           msg.set(39,"45435435");
                           msg.set(58, "00000");  
			   //msg.set(63,"339& 0000200339! QI00317 00000000000000000000 001116341350000 BELKIS A GONZALEZ ROBINSON GONZALEZ 0000037409000012923002 15258545468");                         
                           msg.unset(42);
                        
                           msg.unset(22);
                           
                           }
                         
                         
                         
                        else if(msg.getString(3).equals("430000")){
                           msg.set(4,"000000270000");
                           msg.set(38,"130400");
                           msg.set(59,"00000");
			   msg.set(63,"339& 0000200339! QI00317 00000000000000000000 001116341350000 BELKIS A GONZALEZ ROBINSON GONZALEZ 0000037409000012923002 15258545468");                         
                           msg.unset(42);
                        
                           msg.unset(22);
                           
                           }
                         ///////Consulta pago de servicios PSP////////
                            else if(msg.getString(3).equals("360000")){
                           msg.set(37,"776554");
                           msg.set(38,"130400");
                           msg.set(39,"130400");
                           msg.set(58,"00000");
			   msg.set(61,"339& 0000200339! QI00317 00000000000000000000 001116341350000 BELKIS A GONZALEZ ROBINSON GONZALEZ 0000037409000012923002 15258545468");                         
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
