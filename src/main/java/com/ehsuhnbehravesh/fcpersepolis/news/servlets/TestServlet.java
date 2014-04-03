/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsBean;
import com.ehsunbehravesh.persepolis.entity.News;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ehsun7b
 */
@WebServlet(urlPatterns = "/testnews")
public class TestServlet extends HttpServlet {

  @Inject
  private NewsBean newsBean;
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<News> newslist = newsBean.readTop("fc-perspolis.com", 10);
    
    PrintWriter writer = resp.getWriter();
    
    for (News news : newslist) {
      writer.write(news.getId() + " " + news.getTitle() + "\n");
    }
    
    writer.write("\nfinished.");
    writer.flush();
    writer.close();
  }
  
  
}
