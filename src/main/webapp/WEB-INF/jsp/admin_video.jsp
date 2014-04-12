<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin - Video</title>
  </head>
  <body>
    <%@include file="admin-jspf/header.jspf" %>

    <div class="content">

      <h2>Videos</h2>
      <div class="grid">
        <table class="grid">
          <tr>
            <th>Title</th>
            <th>Image</th>
            <th>Action</th>
          </tr>
          <c:forEach items="${videos}" var="video" varStatus="loop">
            <tr>
              <td>${video.title}</td>
              <td>
                <img style="max-width: 100px" src="/rest/video/image/${video.image}"/>
              </td>
              <td></td>
            </tr>
          </c:forEach>
        </table>
      </div>

      <h2>New Video</h2>
      <div class="form">
        <div class="warning">${warning}</div>
        <div class="info">${info}</div>
        <form method="post" enctype="multipart/form-data">
          <table class="form">
            <tr>
              <td class="caption"><label for="title" />Title:</td>
              <td class="data">
                <input type="text" id="title" name="title"/>
              </td>
            </tr>
            <tr>
              <td class="caption"><label for="category" />Category:</td>
              <td class="data">
                <select name="category" id="category">
                  <c:forEach items="${categories}" var="category">
                    <option value="${category.code}">${category.title}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td class="caption"><label for="image" />Image:</td>
              <td class="data">
                <input type="file" id="image" name="image"/>
              </td>
            </tr>
            <tr>
              <td class="caption"><label for="embedCode" />Embed code:</td>
              <td class="data">
                <textarea id="embedCode" name="embedCode"></textarea>
              </td>
            </tr>
            <tr>
              <td class="buttons" colspan="2">
                <input type="submit" name="ok" value="Save"/>                       
              </td>              
            </tr>
          </table>
          <script>
            $("#title").focus();
          </script>
        </form>
      </div>

      <hr/>
      
      <h2>Video Categories</h2>
       <div class="grid">
        <table class="grid">
          <tr>
            <th>Code</th>
            <th>Title</th>
            <th>Action</th>
          </tr>
          <c:forEach items="${categories}" var="category" varStatus="loop">
            <tr>
              <td>${category.code}</td>
              <td>${category.title}</td>
              <td></td>
            </tr>
          </c:forEach>
        </table>
      </div>
      
      <h2>New Category</h2>
      <div class="form">
        <form method="post" action="/admin/videocategory">
          <table class="form">
            <tr>
              <td class="caption"><label for="code" />Code:</td>
              <td class="data">
                <input type="number" id="code" name="code"/>
              </td>
            </tr>
            <tr>
              <td class="caption"><label for="title" />Title:</td>
              <td class="data">
                <input type="text" id="title" name="title"/>
              </td>
            </tr>
            <tr>
              <td class="buttons" colspan="2">
                <input type="submit" name="ok" value="Save"/>                       
              </td>              
            </tr>
          </table>          
        </form>
      </div>

      
    </div>
  </body>
</html>
