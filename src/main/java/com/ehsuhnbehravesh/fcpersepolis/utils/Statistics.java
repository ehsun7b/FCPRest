package com.ehsuhnbehravesh.fcpersepolis.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

public class Statistics {

  /*
	public static Key registerAccess(DatastoreService ds, HttpServletRequest request) {
		String useragent = request.getHeader("User-Agent");
		String ip = request.getRemoteAddr();
		Calendar calendar = new GregorianCalendar();
		Date time = calendar.getTime();
		String uri = request.getRequestURI();
		String method = request.getMethod();
		
		Entity visit = new Entity("Visit");
		
		visit.setProperty("ip", ip);
		visit.setProperty("userAgent", useragent);
		visit.setProperty("time", time.getTime());
		visit.setProperty("uri", uri);
		visit.setProperty("method", method);
		
		Key key = ds.put(visit);
		return key;
	}

	public static long countAccessLogs(DatastoreService ds) {
		Query query = new Query("Visit");
		query.addSort("time", Query.SortDirection.DESCENDING);
		PreparedQuery prepQuery = ds.prepare(query);		
		int countEntities = prepQuery.countEntities(FetchOptions.Builder.withLimit(10000));
		return countEntities;
	}
	
	public static Iterable<Entity> loadAccessLogs(DatastoreService ds) {
		Query query = new Query("Visit");
		query.addSort("time", Query.SortDirection.DESCENDING);
		PreparedQuery prepQuery = ds.prepare(query);		
		Iterable<Entity> iterable = prepQuery.asIterable(FetchOptions.Builder.withLimit(30));
		return iterable;
	}*/
}
