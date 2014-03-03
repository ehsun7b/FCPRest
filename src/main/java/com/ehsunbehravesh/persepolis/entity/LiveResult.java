package com.ehsunbehravesh.persepolis.entity;

/**
 *
 * @author ehsun7b
 */
public class LiveResult {

  private String homeTeam;
  private String awayTeam;
  private int homeScore;
  private int awayScore;
  private String time;  

  public String getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(String homeTeam) {
    this.homeTeam = homeTeam;
  }

  public String getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(String awayTeam) {
    this.awayTeam = awayTeam;
  }

  public int getHomeScore() {
    return homeScore;
  }

  public void setHomeScore(int homeScore) {
    this.homeScore = homeScore;
  }

  public int getAwayScore() {
    return awayScore;
  }

  public void setAwayScore(int awayScore) {
    this.awayScore = awayScore;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }
}
