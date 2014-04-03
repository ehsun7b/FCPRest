package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/message"})
@SuppressWarnings("serial")
public class MessageServlet extends HttpServlet {

  private static final Logger log = Logger.getLogger(MessageServlet.class.getName());

  private Message message;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    message = populateMessage();

    writeResponse(resp.getWriter(), req.getParameter("callback"));
    resp.getWriter().close();
    resp.flushBuffer();
  }

  private Message populateMessage() {
    Message result = new Message();

    String html = "";

    result.setShow(true);
    result.setTitle("سخنی با شما");
    result.setHtml(html);

    return result;
  }

  private void writeResponse(PrintWriter writer, String callback) {
    if (callback != null) {
      writer.write(callback);
    }

    Gson gson = new Gson();

    String json = gson.toJson(message);

    writer.write("(".concat(json).concat(")"));
  }

  private class Message {

    private boolean show;
    private String title;
    private String html;

    public boolean isShow() {
      return show;
    }

    public void setShow(boolean show) {
      this.show = show;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getHtml() {
      return html;
    }

    public void setHtml(String html) {
      this.html = html;
    }
  }

}
