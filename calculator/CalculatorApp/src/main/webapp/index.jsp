<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP Calculator</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <div class="calculator">
        <h2>JSP Calculator</h2>
        <form action="CalculatorServlet" method="post">
            <input type="text" name="num1" required placeholder="Enter number">
            <select name="operator">
                <option value="+">+</option>
                <option value="-">-</option>
                <option value="*">*</option>
                <option value="/">/</option>
            </select>
            <input type="text" name="num2" required placeholder="Enter number">
            <button type="submit">Calculate</button>
            <button type="reset">C</button>
        </form>

        <% if (request.getAttribute("result") != null) { %>
            <h3>Result: <%= request.getAttribute("result") %></h3>
        <% } else if (request.getAttribute("error") != null) { %>
            <h3 style="color: red;"><%= request.getAttribute("error") %></h3>
        <% } %>

        <h3>Calculation History</h3>
        <ul>
            <% 
                ArrayList<String> history = (ArrayList<String>) session.getAttribute("history");
                if (history != null) {
                    for (String entry : history) {
            %>
                <li><%= entry %></li>
            <% 
                    }
                }
            %>
        </ul>
    </div>
</body>
</html>
