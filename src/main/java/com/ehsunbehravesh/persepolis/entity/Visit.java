package com.ehsunbehravesh.persepolis.entity;

import java.util.Calendar;
import java.util.Date;

import com.ehsuhnbehravesh.fcpersepolis.utils.Utils;

public class Visit {
/*
  public Visit(Entity entity) {
    super();
    this.id = entity.getKey().getId();
    this.method = (String) entity.getProperty("method");
    this.ip = (String) entity.getProperty("ip");
    this.uri = (String) entity.getProperty("uri");
    this.time = (long) entity.getProperty("time");
    this.userAgent = (String) entity.getProperty("userAgent");
  }
*/
  private long id;
  private String ip;
  private String method;
  private String uri;
  private long time;
  private String userAgent;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Date getDate() {
    Calendar calendar = Utils.getCalendar();
    calendar.setTimeInMillis(time);
    return calendar.getTime();
  }
}
