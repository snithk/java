package com.calculator;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
        String operator = request.getParameter("operator");

        double result = 0;
        String error = null;

        try {
            double n1 = Double.parseDouble(num1);
            double n2 = Double.parseDouble(num2);

            switch (operator) {
                case "+": result = n1 + n2; break;
                case "-": result = n1 - n2; break;
                case "*": result = n1 * n2; break;
                case "/": 
                    if (n2 == 0) {
                        error = "Cannot divide by zero";
                    } else {
                        result = n1 / n2;
                    }
                    break;
                default: error = "Invalid Operator";
            }
        } catch (NumberFormatException e) {
            error = "Invalid Input";
        }

        HttpSession session = request.getSession();
        ArrayList<String> history = (ArrayList<String>) session.getAttribute("history");
        if (history == null) {
            history = new ArrayList<>();
        }

        if (error == null) {
            String entry = num1 + " " + operator + " " + num2 + " = " + result;
            history.add(entry);
            request.setAttribute("result", result);
        } else {
            request.setAttribute("error", error);
        }

        session.setAttribute("history", history);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
