<%-- 
    Document   : resetNewPassword
    Created on : Apr 9, 2022, 6:26:20 PM
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
        <h1>Enter a new password</h1>
        <form action="reset" method="POST">
            <input type="password" id="newpass" name="newpass">
            <input type="submit" value="Submit">
            <input type="hidden" id="trashUUID" name="trashUUID" value="${trashUUID}">
            
        </form>
    </body>
</html>
