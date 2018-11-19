package com.sda.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message","ala ma kota");

        req.setAttribute("url", "http://www.wp.pl");
        req.setAttribute("text","WP");
        req.setAttribute("age",15);
        req.setAttribute("age2",20);

        req.getRequestDispatcher("test.jsp").forward(req,resp);
    }
}
