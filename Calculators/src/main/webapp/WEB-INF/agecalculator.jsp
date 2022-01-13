<%-- 
    Document   : agecalculator
    Created on : Jan. 12, 2022, 8:40:01 p.m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Age Calculator</title>
        <link href="/Calculators/styles/master.css" rel="stylesheet"></link>
    </head>
    <body>
        <h1>Age Calculator</h1>
        <form action="age" method="POST">
            <label for="age">Enter your age: </label>
            <input type="number" name="age" id="age"></input>
            <button type="submit"><strong>Age up 1 year</strong></button>
        </form>
        
        <p>${message}</p>
        <a href="/Calculators/calculator">Arithmetic Calculator</a>
    </body>
</html>
