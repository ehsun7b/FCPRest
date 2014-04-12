<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin - Hot News</title>
  </head>
  <body>
    <%@include file="admin-jspf/header.jspf" %>

    <div class="content">

      <div class="grid">
        <table class="grid">
          <tr>
            <th>ID</th>
            <th>News List</th>
          </tr>
          <c:forEach items="${hotNewsList}" var="hotNews" varStatus="loop">
            <tr>
              <td>${hotNews.id}</td>
              <td>
                <c:forEach items="${hotNews.newsList}" var="news">${news.uniqueKey},</c:forEach>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>

      New keys:
      <div class="form">
        <form method="post">
          <input name="newsId" id="newsId"/>
          <input type="submit" value="Save" name="ok"/>
        </form>
      </div>

    </div>
  </body>
</html>
