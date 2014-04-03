package com.ehsuhnbehravesh.fcpersepolis.net;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ehsuhnbehravesh.fcpersepolis.news.Ranking;
import com.ehsuhnbehravesh.fcpersepolis.news.RankingNode;

/**
 *
 * @author ehsun.behravesh
 */
public class RankingFetch {

  private static final String URL = "http://www.varzesh3.com/leaguecomshow.do?lang=en&machesid=standing90-900102-900352-full";
  private Ranking ranking;

  public Ranking load() throws Exception {
    Document doc = getDocument(URL);
    doc = doc.normalise();
    Elements divs = doc.select("#anc-op");
    if (divs != null && divs.size() > 0) {
      Element div = divs.get(0);
      Element table = div.child(2);
      Elements trs = table.select("tr");

      ranking = new Ranking();
      for (int i = 3; i < trs.size(); ++i) {
        Element tr = trs.get(i);
        RankingNode node = new RankingNode();
        Elements tds = tr.select("td");

        Element tdName = tds.get(1);
        String name = tdName.text();
        node.setTeam(name);

        Element tdMatches = tds.get(2);
        String matches = tdMatches.text();
        node.setMatches(matches);

        Element tdWins = tds.get(3);
        String wins = tdWins.text();
        node.setWins(wins);

        Element tdDraws = tds.get(4);
        String draws = tdDraws.text();
        node.setDraws(draws);

        Element tdLooses = tds.get(5);
        String looses = tdLooses.text();
        node.setLooses(looses);

        Element tdScored = tds.get(6);
        String scored = tdScored.text();
        node.setScoredGoals(scored);

        Element tdReceived = tds.get(7);
        String received = tdReceived.text();
        node.setReceivedGoals(received);

        Element tdDifference = tds.get(8);
        String difference = tdDifference.text();
        node.setDifferentGoals(difference);

        Element tdPoints = tds.get(9);
        String points = tdPoints.text();
        node.setPoints(points);

        ranking.getTable().add(node);
      }
      return ranking;
    } else {
      throw new Exception("Can not load ranking table!");
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
