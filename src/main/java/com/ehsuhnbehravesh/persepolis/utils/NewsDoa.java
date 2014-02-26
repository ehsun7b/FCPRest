package com.ehsuhnbehravesh.persepolis.utils;

import com.ehsuhnbehravesh.persepolis.news.News;
import com.ehsunbehravesh.persepolis.entity.NewsLike;

public class NewsDoa {
/*
	public static Key insertNews(DatastoreService ds, News news) {
		Entity visit = new Entity("News");

		visit.setProperty("title", news.getTitle());
		visit.setProperty("description", news.getDescription());
		visit.setProperty("time", news.getFirstLikeTime());
		visit.setProperty("url", news.getLink());

		Key key = ds.put(visit);
		return key;
	}

	public static Key insertNewsLike(DatastoreService ds, NewsLike like) {
		Entity newsLike = new Entity("NewsLike");

		newsLike.setProperty("newsId", like.getNewsId());
		newsLike.setProperty("uuid", like.getUuid());
		newsLike.setProperty("time", like.getTime());

		Key key = ds.put(newsLike);
		return key;
	}

	public static boolean checkLike(DatastoreService ds, NewsLike like) {
		Filter uuidFilter = new FilterPredicate("uuid", FilterOperator.EQUAL,
				like.getUuid());
		Filter newsIdFilter = new FilterPredicate("newsId", FilterOperator.EQUAL,
				like.getNewsId());
		Filter filter = CompositeFilterOperator.and(uuidFilter, newsIdFilter);
		Query query = new Query("NewsLike");
		query.setFilter(filter);

		PreparedQuery prepQuery = ds.prepare(query);
		return prepQuery.asIterable().iterator().hasNext();
	}
	
	public static long countLike(DatastoreService ds, long newsId) {		
		Filter newsIdFilter = new FilterPredicate("newsId", FilterOperator.EQUAL,
				newsId);		
		Query query = new Query("NewsLike");
		query.setFilter(newsIdFilter);

		PreparedQuery prepQuery = ds.prepare(query);		
		int countEntities = prepQuery.countEntities(FetchOptions.Builder.withLimit(10000));			
					
		return countEntities;
	}

	public static Entity findById(DatastoreService ds, long id) {
		Filter idFilter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY,
				FilterOperator.EQUAL, KeyFactory.createKey("News", id));
		Query query = new Query("News");
		query.setFilter(idFilter);

		PreparedQuery prepQuery = ds.prepare(query);

		if (prepQuery.asIterable().iterator().hasNext()) {
			return prepQuery.asIterable().iterator().next();
		} else {
			return null;
		}
	}

	public static Entity findByUrl(DatastoreService ds, String url) {
		Filter urlFilter = new FilterPredicate("url", FilterOperator.EQUAL, url);
		Query query = new Query("News");
		query.setFilter(urlFilter);

		PreparedQuery prepQuery = ds.prepare(query);

		if (prepQuery.asIterable().iterator().hasNext()) {
			return prepQuery.asIterable().iterator().next();
		} else {
			return null;
		}
	}
	
	public static Iterable<Entity> newsWithoutDescription(DatastoreService ds) {
		Filter descNullFilter = new FilterPredicate("description", FilterOperator.EQUAL, null);
		Query query = new Query("News");
		query.setFilter(descNullFilter);

		PreparedQuery prepQuery = ds.prepare(query);

		return prepQuery.asIterable(FetchOptions.Builder.withLimit(10));		
	}
*/
}
