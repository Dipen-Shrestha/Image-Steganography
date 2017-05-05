package StegoTryPackage;


import java.awt.Color;

/******************************
 * Least Significant Bit Encoding and Decoding
 * CSC 350 Fall 2008
 * Mark Goadrich 
 *
 * This class will bring in an image, and either encode
 * a message in the least significant bit of the pixels,
 * or decode a message that is already there.
 * For encoding, you must load a jpg, and save it as a png.
 * For decoding, you must load a png
 ******************************/
public class lsbClass {

    private Picture p;
    private String ctype;
    private String message;
    private String saveFile;

    /*************
     * The constructor for an LSB object. It brings in the 
     * command line arguments and sets up the data members
     *************/
    public lsbClass (String[] args) {
        try {
            // get the picture from the user
            p = new Picture(args[0]);

            // get the conversion type from the user, 
            // E for Encode or D for Decode
            ctype = args[1];
    
            message = "";
    
            // if encrypting, 
            if (ctype.equals("E")) {
        
                // get the message from the user  
                message = args[2];
        
                // get name of file for saving after encoding
                saveFile = args[3];
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println("Usage: java LSB <fileToRead> <codingType> <message> <fileToSave>");
            System.out.println("  where codingType is E for Encode or D for Decode");
            System.out.println("        message is a string enclosed in \"\"");
            System.exit(-1);    
        } catch (RuntimeException re) {
        	System.out.println(args[0] + " cannot be found to read.");
			System.exit(-1);
		}
    }
    
    /*************
     * Encodes or decodes the image.  This is where you will
     * need to add code, based on the algorithm discussed in class
     * and in the book on page 11.  You will need to use the Picture class
     * also provided, and the Color class from the standard java API.
     *************/
    public void convert() {
        int count = 0;

        // Turn message into bits if we're encoding
        if (ctype.equals("E")) {
            message = convertToBinary(message);
        }

        // analyze the image accordingly
        for (int i = 0; i < p.width(); i++) {
            for (int j = 0; j < p.height(); j++) {
                Color c = p.get(i, j);

                // if decoding, get the least significant bit from each color in each pixel, RGB
                // and convert them to ascii characters. 
                if (ctype.equals("D")) {
                    if (c.getRed() % 2 == 1) {
                        message += "1";
                    } else {
                        message += "0";
                    }                    
                    if (c.getGreen() % 2 == 1) {
                        message += "1";
                    } else {
                        message += "0";
                    }
                    if (c.getBlue() % 2 == 1) {
                        message += "1";
                    } else {
                        message += "0";
                    }
                    if (message.length() > 7) {
                        char pc = convertBinary(message.substring(0, 8));
                        if (pc <= 31 || pc >= 127)  {
                            pc = '*';
                        }
                        System.out.print(pc);
                        message = message.substring(8, message.length());
                    }
                } else if (ctype.equals("E")) {
                    // if encoding, change the color based on the ith bit in the 
                    // message bitstring
    
                    int newRed = c.getRed();
                    if (count < message.length() &&
                        c.getRed() % 2 != Integer.parseInt("" + message.charAt(count))) {
                        if (c.getRed() % 2 == 0) {
                            newRed += 1;
                        } else {
                            newRed -= 1;
                        }
                    }
                    count++;
                
                    int newGreen = c.getGreen();
                    if (count < message.length() &&
                        c.getGreen() % 2 != Integer.parseInt("" + message.charAt(count))) {
                        if (c.getGreen() % 2 == 0) {
                            newGreen += 1;
                        } else {
                            newGreen -= 1;
                        }
                    }
                    count++;
    
                    int newBlue = c.getBlue();
                    if (count < message.length() &&
                        c.getBlue() % 2 != Integer.parseInt("" + message.charAt(count))) {
                        if (c.getBlue() % 2 == 0) {
                            newBlue += 1;
                        } else {
                            newBlue -= 1;
                        }
                    }
                    count++;

                    p.set(i, j, new Color(newRed, newGreen, newBlue));
                }  
            }
        
        }
        
        // save images as new if encoding
        if (ctype.equals("E")) {
            p.save(saveFile);
        }
    }
    
    /*************
     * Brings in a message and sequentially converts each letter into binary.
     *************/
    public String convertToBinary(String message) {
        String bin = "";
        for (int i = 0; i < message.length(); i++) {
            bin += convertLetter(message.charAt(i));
        }
        return bin;
    }

    /*************
     * Brings in an ASCII character, and return a binary representation of that number
     *************/
    public String convertLetter(char c) {
        int x = (int)(c);
        String bin = "";
        for (int i = 0; i < 8; i++) {
            bin = "" + (x % 2) + bin;
            x = x / 2;
        }
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
        return (char)num;
    }

    /*************
     * The main method for our class.  We pass the args to create
     * an lsbClass object, then call the convert method to process the data.
     *************/
    public static void main(String[] args) {
        lsbClass lsb = new lsbClass(args);
        lsb.convert();    
    }
}