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
import java.io.IOException; 
import static java.time.temporal.WeekFields.ISO;
import java.util.Date;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOFilter;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISODate;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.channel.LoopbackChannel;
import org.jpos.iso.packager.ISO87APackager;

import org.jpos.util.LogEvent;


/**
 *
 * @author wellington.perez
 */
public class transaccion implements ISOFilter {
      public static void main (String[] args){
     try{
         new transaccion().run();
     
     }catch(Exception e){
         e.printStackTrace();
     
     }
 
 
 }
      
public void run ()throws ISOException, IOException{
     LoopbackChannel channel = new LoopbackChannel();
     channel.addIncomingFilter(this);
     ISOMsg request = createRequest();
     request.dump(System.out, "request>");
     channel.send(request);
     ISOMsg response = channel.receive();
     response.dump(System.out, "response>");
      }
       
 private ISOMsg createRequest () throws ISOException{
 ISOMsg m = new ISOMsg();
 m.setHeader("ISO011000017".getBytes());
 m.setMTI("0800");
 m.set(1,"0400000000000000");
 m.set(7,ISODate.getDateTime(new Date()));
 m.set(11,"59540");
 m.set(70,"Network-Mgmt-Info-Code = 001");
 
 
 
 //byte[] data = m.pack();
 //System.out.println(ISOUtil.hexdump(data));
 
 
 return m;
 
 }
    public ISOMsg filter (ISOChannel channel, ISOMsg m, LogEvent evt){
     try{
         m.setResponseMTI();
         m.set(39, "00");
         
     }catch(ISOException e){
         e.printStackTrace();
     
     }
     return m;
 
 
 }
      
      
    
}
