package com.ehsuhnbehravesh.fcpersepolis.news;

public class MatchInfo {
	private String homeTeam;
	private String guestTeam;
	private String homeTeamLogo;
	private String guestTeamLogo;
	private String matchDescription;
	private Integer homeTeamScore;
	private Integer guestTeamScore;

	public Integer getHomeTeamScore() {
		return homeTeamScore;
	}

	public void setHomeTeamScore(Integer homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	public Integer getGuestTeamScore() {
		return guestTeamScore;
	}

	public void setGuestTeamScore(Integer guestTeamScore) {
		this.guestTeamScore = guestTeamScore;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}

	public String getHomeTeamLogo() {
		return homeTeamLogo;
	}

	public void setHomeTeamLogo(String homeTeamLogo) {
		this.homeTeamLogo = homeTeamLogo;
	}

	public String getGuestTeamLogo() {
		return guestTeamLogo;
	}

	public void setGuestTeamLogo(String guestTeamLogo) {
		this.guestTeamLogo = guestTeamLogo;
	}

	public String getMatchDescription() {
		return matchDescription;
	}

	public void setMatchDescription(String matchDescription) {
		this.matchDescription = matchDescription;
	}
}
