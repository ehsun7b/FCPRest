<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Login</title>
    <script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
  </head>
  <body>
    <div class="login">
      <div class="message">${message}</div>
      <form method="post">
        <table class="loginForm">
          <tr>
            <td>
              <label for="username">Username:</label>
            </td>
            <td>
              <input name="username" id="username"/>
            </td>
          </tr>
          <tr>
            <td>
              <label for="password">Password:</label>
            </td>
            <td>
              <input name="password" id="password" type="password"/>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <input type="submit" name="ok" value="Sign In"/>
            </td>
          </tr>
        </table>
      </form>
      <script>
        $("#username").focus();
      </script>
        
    </div>
  </body>
</html>
