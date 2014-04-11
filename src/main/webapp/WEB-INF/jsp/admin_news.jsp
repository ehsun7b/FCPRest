<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin - News</title>
  </head>
  <body>
    <%@include file="admin-jspf/header.jspf" %>

    <div class="content">

      <div class="grid">
        <table class="grid">
          <tr>            
            <th>KEY</th>
            <th>TITLE</th>
            <th>DESCRIPTION</th>
            <th>IMAGE</th>
            <th>ACTION</th>
          </tr>
          <c:forEach items="${newsList}" var="news" varStatus="loop">
            <tr>
              <td>${news.uniqueKey}</td>
              <td><a href="/news/${news.uniqueKey}">${news.title}</a></td>
              <td>${news.description}</td>
              <td><img src="/rest/image/news/${news.uniqueKey}/70/70"/></td>
            </tr>
          </c:forEach>
        </table>
      </div>

    </div>
  </body>
</html>
