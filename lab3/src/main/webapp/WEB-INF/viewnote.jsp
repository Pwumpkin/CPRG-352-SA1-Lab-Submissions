<%-- 
    Document   : viewnote
    Created on : Jan. 27, 2022, 10:04:14 a.m.
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
        <h1>View Note</h1>
        
        <p>Title: ${note.title}</p>
        <p>Contents: ${note.contents}</p>
        
        
        <a href="note?edit=true">Edit Note</a>
    </body>
</html>
