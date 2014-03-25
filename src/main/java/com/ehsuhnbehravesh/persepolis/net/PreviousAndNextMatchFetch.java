package com.ehsuhnbehravesh.persepolis.net;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ehsuhnbehravesh.persepolis.news.MatchInfo;
import com.ehsuhnbehravesh.persepolis.news.PreviousNextMatchInfo;

public class PreviousAndNextMatchFetch {

  private static final String URL = "http://www.arteshesorkh.com/";

  private PreviousNextMatchInfo previousNextMatchInfo;

  public PreviousNextMatchInfo getPreviousNextMatchInfo() {
    return previousNextMatchInfo;
  }

  public void setPreviousNextMatchInfo(
          PreviousNextMatchInfo previousNextMatchInfo) {
    this.previousNextMatchInfo = previousNextMatchInfo;
  }

  public void load() throws Exception {
    Document doc = getDocument(URL);
    doc = doc.normalise();

    try {
      Element divPreGame = doc.select("div#football_game_2").get(0);
      Element divNextGame = doc.select("div#football_game_1").get(0);

      Element imgPreGameTeam1 = divPreGame.select("div.img_game_1").get(0)
              .select("img").get(0);
      String imgSrcPreGameTeam1 = imgPreGameTeam1.attr("src");
      String namePreGameTeam1 = imgPreGameTeam1.attr("title");

      Element imgPreGameTeam2 = divPreGame.select("div.img_game_2").get(0)
              .select("img").get(0);
      String imgSrcPreGameTeam2 = imgPreGameTeam2.attr("src");
      String namePreGameTeam2 = imgPreGameTeam2.attr("title");

      Elements textPreGame = divPreGame.select("div.text_game_1");
      String txtPreGame = textPreGame.text().trim();

      String result = divPreGame.select("div.natije_bazi").get(0).text().trim();
      String[] resultParts = result.split("-");
      int homeScore = Integer.parseInt(resultParts[0].trim());
      int guestScore = Integer.parseInt(resultParts[1].trim());
      // next game

      Element imgNextGameTeam1 = divNextGame.select("div.img_game_1").get(0)
              .select("img").get(0);
      String imgSrcNextGameTeam1 = imgNextGameTeam1.attr("src");
      String nameNextGameTeam1 = imgNextGameTeam1.attr("title");

      Element imgNextGameTeam2 = divNextGame.select("div.img_game_2").get(0)
              .select("img").get(0);
      String imgSrcNextGameTeam2 = imgNextGameTeam2.attr("src");
      String nameNextGameTeam2 = imgNextGameTeam2.attr("title");

      Elements textNextGame = divNextGame.select("div.text_game_1");
      String txtNextGame = textNextGame.text().trim();

      previousNextMatchInfo = new PreviousNextMatchInfo();
      MatchInfo preMatch = new MatchInfo();
      MatchInfo nextMatch = new MatchInfo();

      preMatch.setHomeTeam(namePreGameTeam1);
      preMatch.setHomeTeamLogo(imgSrcPreGameTeam1);
      preMatch.setGuestTeam(namePreGameTeam2);
      preMatch.setGuestTeamLogo(imgSrcPreGameTeam2);
      preMatch.setMatchDescription(txtPreGame);
      preMatch.setHomeTeamScore(homeScore);
      preMatch.setGuestTeamScore(guestScore);

      nextMatch.setHomeTeam(nameNextGameTeam1);
      nextMatch.setHomeTeamLogo(imgSrcNextGameTeam1);
      nextMatch.setGuestTeam(nameNextGameTeam2);
      nextMatch.setGuestTeamLogo(imgSrcNextGameTeam2);
      nextMatch.setMatchDescription(txtNextGame);

      previousNextMatchInfo.setPreMatch(preMatch);
      previousNextMatchInfo.setNextMatch(nextMatch);
    } catch (Exception ex) {
      throw ex;
    }
  }

  private Document getDocument(String url) {
    Document doc = null;
    while (doc == null) {
      try {
        doc = Jsoup.connect(url).get();
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    return doc;
  }
}
