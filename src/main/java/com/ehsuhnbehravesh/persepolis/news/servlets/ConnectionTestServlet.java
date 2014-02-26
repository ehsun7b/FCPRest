package com.ehsuhnbehravesh.persepolis.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/connectiontest"})
@SuppressWarnings("serial")
public class ConnectionTestServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    writeResponse(resp.getWriter(), req.getParameter("callback"));
    resp.getWriter().close();
    resp.flushBuffer();
  }

  private void writeResponse(PrintWriter writer, String callback) {
    if (callback != null) {
      writer.write(callback);
    }

    Gson gson = new Gson();

    String json = gson.toJson(1);

    writer.write("(".concat(json).concat(")"));
  }

}
