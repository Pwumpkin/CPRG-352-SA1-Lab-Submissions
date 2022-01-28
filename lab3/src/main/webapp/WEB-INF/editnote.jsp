<%-- 
    Document   : editnote
    Created on : Jan. 27, 2022, 10:04:31 a.m.
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
        <h1>Edit Note</h1>
        <form action="note" method="POST">
            <label for="title">Title: <input type="text" name="title" id="title"></input></label><!-- comment -->
            <label for="">Contents: </label><!-- comment -->
            <textarea id="contents" name="contents" rows="5" cols="10"></textarea>
            <button type="submit">Save</button>
        </form>
    </body>
</html>
