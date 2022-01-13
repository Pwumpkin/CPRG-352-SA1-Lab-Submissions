<%-- 
    Document   : arithmeticcalculator
    Created on : Jan. 12, 2022, 9:33:36 p.m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Arithmetic Calculator</title>
    </head>
    <body>
        <h1>Arithmetic Calculator</h1>
        <form action="/Calculators/calculator" method="POST">
            <label>First Number: <input type="number" name="operand1" id="operand1"></input></label>
            <br>
            <label>Second Number: <input type="number" name="operand2" id="operand2"></input></label>
            <div>
                <button type="submit" name="operation" id="addition" value="add">+</button>
                <button type="submit" name="operation" id="subtraction" value="subtract">-</button>
                <button type="submit" name="operation" id="multiplication" value="multiply">*</button>
                <button type="submit" name="operation" id="division" value="divide">/</button>
            </div>
        </form>
        <br>
        
        <p><span>Result: </span>${output}</p>
        <a href="age">Age Calculator</a>
    </body>
</html>
