package com.ehsuhnbehravesh.persepolis.utils;

public class ImageDao {
/*
  public static Key insertImage(DatastoreService ds, String url, byte[] content) {
    Entity visit = new Entity("Image");

    visit.setProperty("url", url);
    visit.setProperty("content", new Blob(content));

    Key key = ds.put(visit);
    return key;
  }

  public static Entity loadImage(DatastoreService ds, String url) {
    Filter urlFilter = new FilterPredicate("url", FilterOperator.EQUAL, url);
    Query query = new Query("Image");
    query.setFilter(urlFilter);

    PreparedQuery prepQuery = ds.prepare(query);
    if (prepQuery.asIterable().iterator().hasNext()) {
      return prepQuery.asIterable().iterator().next();
    } else {
      return null;
    }
  }*/
}
