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
import org.jpos.iso.*;
import org.jpos.iso.channel.*; 
import org.jpos.iso.packager.*;
import java.io.*;
import org.jpos.MyChannel;
import org.jpos.MyPackager;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.iso.packager.BASE24Packager;
import org.jpos.iso.channel.BASE24TCPChannel;





/**
 *
 * @author wellington.perez
 */
public class Test  implements ISORequestListener{
    public Test(){
    super();
    }
    @Override
    public boolean process (ISOSource source, ISOMsg msg){
      Test p = new  Test();
     
      
        try{
                         logISOMsg(msg);
                         msg.setResponseMTI();
                         if(msg.getMTI().equals("0210")){
                       // msg.setResponseMTI();
                        msg.set(38,"123456");
                        msg.set(39,"00");
                        msg.set(44,"0000000000000000000000000");
                        msg.set(54,"000000000000");
                        msg.set(59, "123");
                        //msg.setResponseMTI();
                        //msg.unset("52");
                         }
                        if(msg.getValue(52)!=null){
                            msg.unset("52");
                        }
          
            source.send(msg);
            //p.logISOMsg(msg);
         
        
        }catch(ISOException e ){
            e.printStackTrace();
        
        }catch(IOException e){
            e.printStackTrace();
            
        
        }
        return true;
        
    
    
    }
public static void logISOMsg(ISOMsg msg) {
System.out.println("----ISO MESSAGE-----");
try {
    
System.out.println("  MTI : " + msg.getMTI());
   for (int i = 1; i <= msg.getMaxField(); i++) {
     if (msg.hasField(i)) {
             System.out.println("    Field-" + i + " : "+ msg.getString(i));
				}
			}
		} catch (ISOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("--------------------");
		}
}

 /*private void printISOMessage(ISOMsg msg) {
        try {
            System.out.printf("MTI = %s%n", msg.getMTI());
            for (int i = 1; i <= msg.getMaxField(); i++) {
                if (msg.hasField(i)) {
                    System.out.printf("Field (%s) = %s%n", i, msg.getString(i));
                }
            }
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }*/
 
public static void main (String[] args) throws Exception{
        
    byte[] TPDU = new byte[12];
    //ISO87APackager PACK = new ISO87APackager();
    //GenericPackager PACK = new GenericPackager();
    MyPackager PACK = new MyPackager();
    ServerChannel channel = new MyChannel(PACK, TPDU);
    ISOServer server = new ISOServer(8091,channel, null);
    server.addISORequestListener(new Test ());
    System.out.println("ISO8583 server started...");

    new Thread(server).start();
    
    
    
    
    
    
    
    



}

    
     
      

    
}
