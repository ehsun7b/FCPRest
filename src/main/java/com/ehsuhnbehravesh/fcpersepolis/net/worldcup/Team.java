package com.ehsuhnbehravesh.fcpersepolis.net.worldcup;

import java.util.List;

/**
 *
 * @author ehsun7b
 */
public class Team {
  
  private String englishName;
  private String persianName;
  private int matches;
  private int wins;
  private int draws;
  private int looses;
  private int scoredGoals;
  private int receivedGoals;

  private List<GroupMatch> groupMatches;

  public List<GroupMatch> getGroupMatches() {
    return groupMatches;
  }

  public void setGroupMatches(List<GroupMatch> groupMatches) {
    this.groupMatches = groupMatches;
  }
  
  public String getEnglishName() {
    return englishName;
  }

  public void setEnglishName(String englishName) {
    this.englishName = englishName;
  }

  public String getPersianName() {
    return persianName;
  }

  public void setPersianName(String persianName) {
    this.persianName = persianName;
  }

  public int getMatches() {
    return matches;
  }

  public void setMatches(int matches) {
    this.matches = matches;
  }

  public int getWins() {
    return wins;
  }

  public void setWins(int wins) {
    this.wins = wins;
  }

  public int getDraws() {
    return draws;
  }

  public void setDraws(int draws) {
    this.draws = draws;
  }

  public int getLooses() {
    return looses;
  }

  public void setLooses(int looses) {
    this.looses = looses;
  }

  public int getScoredGoals() {
    return scoredGoals;
  }

  public void setScoredGoals(int scoredGoals) {
    this.scoredGoals = scoredGoals;
  }

  public int getReceivedGoals() {
    return receivedGoals;
  }

  public void setReceivedGoals(int receivedGoals) {
    this.receivedGoals = receivedGoals;
  }
  
  
}
