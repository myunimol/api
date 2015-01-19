package it.unimol.my.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {

    /**
     * In questo metodo, una stringa contenente non breaking space (che è
     * diverso dal whitespace) viene trimmata all'inizio e alla fine.
     *
     * @param untrimmed stringa non trimmata
     * @return restituisce la stringa trimmata
     */
    public static String realTrim(String untrimmed) {
        String trimmed = untrimmed.replace(String.valueOf((char) 160), " ").trim();
        return trimmed;
    }
    
    public static String md5(String pString) throws NoSuchAlgorithmException {
        // Create MessageDigest instance for MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        //Add password bytes to digest
        md.update(pString.getBytes());
        //Get the hash's bytes
        byte[] bytes = md.digest();
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        //Get complete hashed password in hex format
        return sb.toString();
    }

}
