<%-- 
    Document   : login
    Created on : Feb. 7, 2022, 6:05:30 a.m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="login" method="POST">
            <p>
                <label for="username">Username: <input type="text" name="username" id="username" value="${username}" required></label>
            </p>
            <p>
                <label for="password">Password: <input type="password" name="password" id="password" required></label>
            </p>
            <button type="submit">Submit</button>
            
            <p>${message}</p>
        </form>
    </body>
</html>
