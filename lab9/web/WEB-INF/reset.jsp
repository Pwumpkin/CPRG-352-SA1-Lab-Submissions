<%-- 
    Document   : reset
    Created on : Apr 9, 2022, 7:22:54 AM
    Author     : Coder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <h2 id="message"></h2>
        <form action="reset" method="POST">
            <p>Please enter your email address to reset your password</p>
            <label for"their-email">Email Address:<input type="text" id="their-email"></input></label>
            <input type="submit" value="Get Reset Link">
        </form>
        
    </body>
</html>
