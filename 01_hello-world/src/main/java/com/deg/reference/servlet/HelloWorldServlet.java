package com.deg.reference.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simple Hello World Servlet for Open Liberty
 * Accessible at: http://localhost:9080/hello-world-servlet/hello
 */
@WebServlet(name = "HelloWorldServlet", urlPatterns = {"/hello"})
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello World Servlet</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 50px; background-color: #f0f0f0; }");
            out.println(".container { background-color: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); max-width: 600px; margin: 0 auto; }");
            out.println("h1 { color: #333; }");
            out.println(".info { color: #666; margin-top: 20px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Hello World!</h1>");
            out.println("<p>This is a simple servlet running on Open Liberty.</p>");
            out.println("<div class='info'>");
            out.println("<p><strong>Server Time:</strong> " + getCurrentTime() + "</p>");
            out.println("<p><strong>Servlet Name:</strong> " + getServletName() + "</p>");
            out.println("<p><strong>Context Path:</strong> " + request.getContextPath() + "</p>");
            out.println("<p><strong>Servlet Path:</strong> " + request.getServletPath() + "</p>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Simple Hello World Servlet for Open Liberty";
    }

    private String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
