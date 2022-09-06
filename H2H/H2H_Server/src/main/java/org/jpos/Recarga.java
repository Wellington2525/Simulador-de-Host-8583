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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException; 
import java.util.Date;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOFilter;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.channel.HEXChannel;
import org.jpos.iso.IFA_NUMERIC;
import org.jpos.iso.ISOBasePackager;


import org.jpos.iso.ISODate;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOFieldPackager;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.channel.LoopbackChannel;
import org.jpos.iso.packager.ISO87APackager;

import org.jpos.util.LogEvent;

/**
 *
 * @author wellington.perez
 */
public class Recarga implements ISOFilter {
    
public static void main (String[] args){
     try{
         new Recarga().run();
     
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

public class ISO extends ISOBasePackager{
    protected ISOFieldPackager fld[]={
        new IFA_NUMERIC(4,"0800"),
        new IFA_NUMERIC(16,"8220000000000000"),
        new IFA_NUMERIC(6,"")
    
    
    };
    
    public ISO(ISOChannel channel, ISOMsg m, LogEvent evt){
         super();
        
        try{
         m.setResponseMTI();
         m.set(39, "00");
     
        setFieldPackager(fld);
     }catch(ISOException e){
         e.printStackTrace();
     
     }
    
    }
      


}

private ISOMsg createRequest () throws ISOException{
 ISOMsg m = new ISOMsg();
 m.setHeader("ISO011000017".getBytes());
 m.setMTI("0200");
 m.set(1,"0000000010000001");
 m.set(3,"380000");
 m.set(4,"000000010000");
 m.set(7, ISODate.getDateTime(new Date()));
 m.set(11, "003295");
 m.set(12,"151152");
 m.set(13,ISODate.getDate(new Date()));
 m.set(17,"0901");
 m.set(18,"1");
 m.set(22,"010");
 m.set(32,"10000000024");
 m.set(35,"97000000000000000000000000=4912101");
 m.set(37,"244376703295");
 m.set(41, "00053767");
 m.set(42,"");
 m.set(43,"COMERCIAL");
 m.set(48,"0519003866         ^ 0025^ 0001 ");
 m.set(49,"214");
 m.set(52,"FFFFFFFFFFFFFFFF ");
 m.set(60,"0024^ 0519^ +000^ 0000");
 m.set(61,"0024"); 
 m.set(62,"NIITID");
 m.set(62,"&^  ^ 00002^ 00056^ !^  ^ P0^ 00032^  ^ 1003");
 m.set(100,"10000000000 ");
 m.set(128,"ABCDEF1234567890");
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
