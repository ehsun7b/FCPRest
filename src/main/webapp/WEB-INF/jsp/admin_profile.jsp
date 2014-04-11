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

      <div class="form">
        <form method="post">
          <table class="form">
            <tr>
              <td class="caption"><label for="oldPassword" />OldPassword:</td>
              <td class="data">
                <input type="password" id="oldPassword" name="oldPassword"/>
              </td>
            </tr>
            <tr>
              <td class="caption"><label for="newPassword" />newPassword:</td>
              <td class="data">
                <input type="password" id="newPassword" name="newPassword"/>
              </td>
            </tr>
            <tr>
              <td class="caption"><label for="confirmPassword" />confirmPassword:</td>
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
