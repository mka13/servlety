package com.sda.servlets.links;

import com.sda.servlets.links.Link;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LinkiServlet extends HttpServlet {

    LinksService links;

    @Override
    public void init() throws ServletException {
        this.links=LinksService.instaceOf();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String link = req.getParameter("link");
        String text= req.getParameter("text");
        StringBuilder redirectBuilder=new StringBuilder();
        redirectBuilder.append(req.getContextPath()).append(req.getServletPath());



        if(link==null || text==null){
            redirectBuilder.append("?error_message=niepoprawne dane");
                   }else{
            Link linkObject = new Link(link, text);
            links.save(linkObject);


        }
        resp.sendRedirect(redirectBuilder.toString());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type","text/html"); //-->renderowanie textu na html
        PrintWriter writer = resp.getWriter();




        writer.println("<div>");
        String error_message = req.getParameter("error_message");
        if(error_message !=null){
            writer.println("<p style=\"color:red;\">" + error_message+"</p>");
        }
            createForm(writer);
        List<Link>linki=links.findAll();
        for (int i = 0; i <linki.size() ; i++) {
            Link link=linki.get(i);
            writer.println("<br/>");
            writer.println("<a href=\""+link.getUrl() +"\">" +link.getText()+"</a>");

        }


//        writer.println("<br/>");
//        writer.println("<a href=\"http://www.google.com\">Google</a>");
//        writer.println("<br/>");
//        writer.println("<a href=\"http://pl.wikipedia.com\">Wikipedia</a>");
//        writer.println("<br/>");
//        writer.println("<a href=\"http://onet.pl\">Onet</a>");
//        writer.println("<br/>");
        writer.println("</div>");


    }

    private void createForm(PrintWriter writer) {
    String form="<form action=\"\" method=\"post\">\n" +
            "   Link: <input type=\"text\" name=\"link\">\n" +
            "    </br>\n" +
            "    Text: <input type=\"text\" name=\"text\">\n" +
            "    <input type=\"submit\">\n" +
            "</form>";
    writer.println(form);
    }
}
