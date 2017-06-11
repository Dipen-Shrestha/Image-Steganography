/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectTry;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;

/**
 *
 * @author Dipen
 */
public class decTryDel {
    BufferedImage p = null;
    String message="",ml="";
    String lmessage="";
       int width;
       int height;
  
    decTryDel() throws IOException{
          File f = new File("F:/sujjj.png"); //image file path
        p = ImageIO.read(f);
        //newImage = image;
        width = p.getWidth();
        height = p.getHeight();
        
    for (int i = 0; i < 8; i++)  {
           int j=0;
        Color c = new Color(p.getRGB(j, i));              
                if (c.getRed() % 2 == 1) {
                        lmessage += "1";
                    } else {
                        lmessage += "0";
                    }                    
                    if (c.getGreen() % 2 == 1) {
                        lmessage += "1";
                    } else {
                        lmessage += "0";
                    }
                    if (c.getBlue() % 2 == 1) {
                        lmessage += "1";
                    } else {
                        lmessage += "0";
                    }
                    if (lmessage.length() > 7) {
                       // System.out.println("m "+message);
                       // System.out.println("mss "+message.substring(0,7));
                        char lpc = convertBinary(lmessage.substring(0, 7));
                        if (lpc <= 31 || lpc >= 127)  {
                            lpc = '*';
                        }
                        ml+=lpc;
//                        System.out.println(" m l "+ml);
//                        System.out.print(lpc);
                        lmessage = lmessage.substring(7, lmessage.length());
//                        System.out.println(" meb "+message);
                        
                    }
                   
    }   
        int len = Integer.parseInt(ml);
//        System.out.println("\n\n\n");
        String newm="";
        int checker,count=0;
        checker=(len*7)+24;
    for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(p.getRGB(i, j)); 
                if(count>=checker){ 
                        //System.out.println("_______________________________________________");
                        break; 
                    }
                if (c.getRed() % 2 == 1) {
                        message += "1";
                        count++;
//                        if(checker==count) break;
                    } else {
                        message += "0";
                        count++;
//                        if(checker==count) break;
                    }                    
                    if (c.getGreen() % 2 == 1) {
                        message += "1";
                        count++;
//                        if(checker==count) break;
                    } else {
                        message += "0";
                        count++;
//                        if(checker==count) break;
                    }
                    if (c.getBlue() % 2 == 1) {
                        message += "1";
                        count++;
//                        if(checker==count) break;
                    } else {
                        message += "0";
                        count++;
//                        if(checker==count) break;
                    }
                    if (message.length() > 7) {
//                        System.out.println("m "+message);
//                        System.out.println("mss "+message.substring(0,7));
                        char pc = convertBinary(message.substring(0, 7));
                        if (pc <= 31 || pc >= 127)  {
                            pc = '*';
                        }
//                        if()
                        newm+=pc;
//                        System.out.print(count + " cpc "+pc);
                        message = message.substring(7, message.length());
//                        System.out.println(" meb "+message);
                        
                    }
                    if(count>=checker){ 
                        //System.out.println("_______________________________________________");
                        break; 
                    }
//                    System.out.println(" check "+checker+" count "+count);
            }
    }
        newm=newm.substring(3);
        System.out.println(" message " +newm);               
    }
    
     public char convertBinary(String bin) {
        int num = 0;
        for (int i = 0; i < 7; i++) {
            num += Math.pow(2, 6 - i) * Integer.parseInt("" + bin.charAt(i));
        }
        //System.out.println("Convert to Binary " +num+" char : "+ (char) num);
        return (char)num;
    }
     
     public static void main(String[] args) throws IOException {
        new decTryDel();
/*String bin="0110000";
    int num=0;
for(int i=0; i<7;i++){
    num += Math.pow(2, 6 - i) * Integer.parseInt("" + bin.charAt(i));
    System.out.println(" pow "+Math.pow(2,7-i));
         System.out.println("num "+num+" and bin char at "+i+" = "+Integer.parseInt(""+bin.charAt(i)));
}
         System.out.println(" char "+(char) num);
  */  }
}

