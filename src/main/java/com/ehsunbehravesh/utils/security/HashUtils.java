package com.ehsunbehravesh.utils.security;

import com.itextpdf.text.pdf.codec.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author ehsun7b
 */
public class HashUtils {

  public static String generateSalt(int length) {
    final Random r = new SecureRandom();
    byte[] salt = new byte[length];
    r.nextBytes(salt);
    return Base64.encodeBytes(salt);
  }
  
  public static String SHA256(String value) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(value.getBytes("UTF-8"));
    byte[] digest = md.digest();
    return Base64.encodeBytes(digest);
  }
  
  public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    System.out.println(SHA256("123"));
  }
}
