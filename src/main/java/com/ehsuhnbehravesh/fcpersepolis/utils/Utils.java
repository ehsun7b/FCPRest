package com.ehsuhnbehravesh.fcpersepolis.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

  private static final Logger log = Logger.getLogger(Utils.class.getName());

  public static Calendar getCalendar() {
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
    return calendar;
  }

  public static String uniqueNewsKeyGen(String link) {
    final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
    String result = "";
    MessageDigest md = null;
    
    try {
      md = MessageDigest.getInstance("SHA-1");
      byte[] digest = md.digest(link.getBytes("UTF-8"));

      char[] chars = new char[2 * digest.length];
      for (int i = 0; i < digest.length; ++i) {
        chars[2 * i] = HEX_CHARS[(digest[i] & 0xF0) >>> 4];
        chars[2 * i + 1] = HEX_CHARS[digest[i] & 0x0F];        
      }

      result = new String(chars).substring(5, 15);
    } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
      log.log(Level.SEVERE, ex.getMessage());
    }
    
    return result;
  }  
}
