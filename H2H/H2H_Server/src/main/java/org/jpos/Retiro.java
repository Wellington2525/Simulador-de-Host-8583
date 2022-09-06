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
import java.util.Date;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOFilter;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISODate;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.channel.LoopbackChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.iso.packager.ISO87BPackager;
import org.jpos.util.LogEvent;

/**
 *
 * @author wellington.perez
 */
public class Retiro implements ISOFilter {
    public static void main (String[] args){
     try{
         new Retiro().run();
     
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
 
 m.setMTI("0200");
 m.set(1,"0000000010000001");
 m.set(2,"16");
 m.set(3,"011000");
 m.set(4,"000000136000");
 m.set(7, ISODate.getDateTime(new Date()));
 m.set(11, "011381");
 m.set(12,"141557");
 m.set(13,ISODate.getDate(new Date()));
 m.set(15,"1005");
 m.set(22,"051");
 m.set(23,"000");
 m.set(32,"123");
 m.set(35,"123342");
 m.set(41, "29110001");
 m.set(42,"111111111100001");
 m.set(43,"FARMACIA ABC CARRT");
 m.set(48,"DF01053132383032DF020A30323933333630313438DF090431313031DF0A06313233343536");
 m.set(49,"360");
 m.set(52,"FBD259991B50D5B5");
 m.set(55,"5E37F52");
 m.set(60,"0024");
 m.set(61,"0024"); 
 m.set(62,"97000000000000000000000000=4912101 ");
 m.set(70,"301");
 m.set(100,"10000000000 ");
 m.set(128,"ABCDEF1234567890");
 //byte[] data = m.pack();
 //System.out.println(ISOUtil.hexdump(data));
 
 
 return m;
 
 }
    @Override
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
