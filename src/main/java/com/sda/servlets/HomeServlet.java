package com.sda.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
//        writer.println("<h1> Hello world </h1>");
//        writer.println("<h2>how are you?</h2>");
//        writer.println("<h3> nowy wpis </h3>");
        req.setAttribute("names", Arrays.asList("Jan","Karloina","Anna"));
        req.getRequestDispatcher("index.jsp").forward(req,resp);


    }
}
