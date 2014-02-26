package com.ehsuhnbehravesh.persepolis.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Utils {

  public static Calendar getCalendar() {
    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
    return calendar;
  }

}
