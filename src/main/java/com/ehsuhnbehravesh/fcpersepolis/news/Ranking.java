package com.ehsuhnbehravesh.fcpersepolis.news;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ehsun.behravesh
 */
public class Ranking {

  private List<RankingNode> table;

  public Ranking() {
    table = new ArrayList<>();
  }

  public List<RankingNode> getTable() {
    return table;
  }
}
