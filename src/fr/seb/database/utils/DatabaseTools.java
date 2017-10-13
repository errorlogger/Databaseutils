
package fr.seb.database.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import javax.xml.bind.DatatypeConverter;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DatabaseTools {
    
    public static String sha1Encode(String pwd) {
        String encoded = "";
        
        MessageDigest crypto;
        try {
            // appel singleton message digest
            crypto = MessageDigest.getInstance("SHA-1");

            crypto.reset();
            crypto.update(pwd.getBytes("UTF-8"));
            byte[] encode = crypto.digest();
            encoded = DatatypeConverter.printHexBinary(encode).toLowerCase();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encoded;
    }
    
    
            
}
