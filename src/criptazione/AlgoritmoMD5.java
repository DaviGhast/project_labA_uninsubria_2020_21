package criptazione;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 *
 */
public class AlgoritmoMD5 {
    /**
     * <code>Converti</code> è un mettodo che data un password in chiaro,
     * la restituisce in criptazione MD5
     * @param password password passata dall'utente
     * @return password convertita
     */
    public static String converti(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes(Charset.forName("UTF-8")));
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                buffer.append(String.format("%x", array[i]));
            }
            return buffer.toString();

        } catch (NoSuchAlgorithmException exc) {
            return null;
        }
    }
}