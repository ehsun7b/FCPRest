package com.ehsuhnbehravesh.fcpersepolis.net.worldcup;

import java.util.List;

/**
 *
 * @author ehsun7b
 */
public class Group {
  
  private String name;
  List<Team> teams;

  public List<Team> getTeams() {
    return teams;
  }

  public void setTeams(List<Team> teams) {
    this.teams = teams;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }    
}
