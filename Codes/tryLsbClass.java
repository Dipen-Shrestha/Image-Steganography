/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectTry;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Dipen
 */
public final class tryLsbClass {
    public tryLsbClass(){
        try{
    BufferedImage image = null;
   // BufferedImage newImage = null;
    int width;
    int height;
    //File f = null;
    Color newC;
    int newRGB;
    //read image
    
        File f = new File("E:/a.jpg"); //image file path
        image = ImageIO.read(f);
        //newImage = image;
        width = image.getWidth();
        height = image.getHeight();
    BufferedImage newImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);;

//     newImage = image.getSubimage(0,0,
//       image.getWidth(),image.getHeight());
   // private Picture p;
    
    String message;
    //String saveFile;
           
    
            message = "";
    
            // if encrypting, 
            if (true) {
        
                // get the message from the user  
                message = "Hello";
        
                // get name of file for saving after encoding
                
            }
            
        int count = 0;
        int sn=0;
        // Turn message into bits if we're encoding
        if (true) {
            message = convertToBinary(message);
        }

        // analyze the image accordingly
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color(image.getRGB(i, j));
                System.out.println("\n old :" + c.getRed());
                // if decoding, get the least significant bit from each color in each pixel, RGB
                // and convert them to ascii characters. 
                 System.out.println(" s.n "+sn);
                 sn++;
              
                
                //Encrypt
               // if (true) { //IF STARTED
                    // if encoding, change the color based on the ith bit in the 
                    // message bitstring
    
                    int newRed = c.getRed();
                    if (count < message.length() &&
                        c.getRed() % 2 != Integer.parseInt("" + message.charAt(count))) {
                        System.out.println(Integer.parseInt("" + message.charAt(count))+"  "+ message.charAt(count));
                        if (c.getRed() % 2 == 0) {
                            newRed += 1;
                            System.out.println(" red + 1 "+Integer.toBinaryString(newRed));
                        } else {
                            newRed -= 1;
                            System.out.println(" red - 1 "+Integer.toBinaryString(newRed));
                        }
                    }
                    count++;
                
                    int newGreen = c.getGreen();
                    if (count < message.length() &&
                        c.getGreen() % 2 != Integer.parseInt("" + message.charAt(count))) {
                        if (c.getGreen() % 2 == 0) {
                            newGreen += 1;
                            System.out.println(" green + 1 "+Integer.toBinaryString(newGreen));
                        } else {
                            newGreen -= 1;
                            System.out.println(" green - 1 "+Integer.toBinaryString(newGreen));
                        }
                    }
                    count++;
    
                    int newBlue = c.getBlue();
                    if (count < message.length() &&
                        c.getBlue() % 2 != Integer.parseInt("" + message.charAt(count))) {
                        if (c.getBlue() % 2 == 0) {
                            newBlue += 1;
                            System.out.println(" blue + 1 "+Integer.toBinaryString(newBlue));
                        } else {
                            newBlue -= 1;
                            System.out.println(" blue - 1 "+Integer.toBinaryString(newBlue));
                        }
                    }
                    count++;
                    newC=new Color(newRed, newGreen, newBlue);
                    //newRGB=newC.getRGB();
                    newImage.setRGB(i, j, newC.getRGB());
                    System.out.println("new: "+newRed+" c "+newC.getRed());
                    
                // } //END IF //newImage.setRGB(i, j, newC.getRGB());
                
                
                
                
                    
            }
        
        }
        
        try{
        ImageIO.write( newImage, "png", new File("E:/adanew.png"));
        }catch(Exception ex){}
       
        
//         for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                newImage.setRGB(i, j, newC.getRGB());
////                Color c = new Color(newImage.getRGB(i, j));
////                System.out.println("\nnew :" + c.getRed());
////                System.out.println("new :" + c.getGreen());
////                System.out.println("new :" + c.getBlue());
//                }
//            }
        //296789
        // save images as new if encoding
        
//           ImageIO.write(newImage, "jpg", new File("E:/ada31.jpg"));
           // save(new File("E:/adafs4.jpg"),newImage);
        
     
    }catch(Exception ex){
        
        }
        
        
    }//end of contructor
    
    
    
     public String convertToBinary(String message) {
        String bin = "";
        for (int i = 0; i < message.length(); i++) {
            bin += convertLetter(message.charAt(i));
        }
         System.out.println("Convert to Binary " +bin);
        return bin;
    }

    /*************
     * Brings in an ASCII character, and return a binary representation of that number
     *************/
    public String convertLetter(char c) {
        int x = (int)(c);
        String bin = "";
        for (int  i = 0; i < 8; i++) {
            bin = "" + (x % 2) + bin;
            x = x / 2;
        }
        
        System.out.println("Convert to Letter "+c + " " +bin);
        return bin;
    }

    /*************
     * Brings in a String representation of a binary number, and returns the 
     * int representation for that number
     *************/
    public char convertBinary(String bin) {
        int num = 0;
        for (int i = 0; i < 8; i++) {
            num += Math.pow(2, 7 - i) * Integer.parseInt("" + bin.charAt(i));
        }
        System.out.println("Convert to Binary " +num+" char : "+ (char) num);
        return (char)num;
    }
    
        private String filename;
        public void save(File file,Image image) {
            
        this.filename = file.getName();
        //if (frame != null) { frame.setTitle(filename); }
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
        suffix = suffix.toLowerCase();
        if (suffix.equals("jpg") || suffix.equals("png")) {
            try { 
                ImageIO.write((RenderedImage) image, suffix, file);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else {
            System.out.println("Error: filename must end in .jpg or .png");
        }
    }
        

        
    public static void main(String[] args) {
        new tryLsbClass();
    }
}
