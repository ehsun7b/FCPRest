<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin - News</title>
    <script src="/js/jquery-1.10.2.min.js"></script>
  </head>
  <body>
    <%@include file="admin-jspf/header.jspf" %>

    <div class="content">

      <div class="form">
        <div class="warning">${warning}</div>
        <div class="info">${info}</div>
        <form method="post">
          <table class="form">
            <tr>
              <td class="caption"><label for="oldPassword" />Old password:</td>
              <td class="data">
                <input type="password" id="oldPassword" name="oldPassword"/>
              </td>
            </tr>
            <tr>
              <td class="caption"><label for="newPassword" />New password:</td>
              <td class="data">
                <input type="password" id="newPassword" name="newPassword"/>
              </td>
            </tr>
            <tr>
              <td class="caption"><label for="confirmPassword" />Confirm password:</td>
              <td class="data">
                <input type="password" id="confirmPassword" name="confirmPassword"/>
              </td>
            </tr>
            <tr>
              <td class="buttons" colspan="2">
                <input type="submit" name="ok" value="Change"/>                       
              </td>              
            </tr>
          </table>
          <script>                        
            $("#oldPassword").focus();
          </script>
        </form>
      </div>

    </div>
  </body>
</html>
