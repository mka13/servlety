package com.sda.servlets.users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class UsersServlet extends HttpServlet {
    private UsersService usersService;


    @Override
    public void init() throws ServletException {
        this.usersService=UsersService.instaceOf();

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<div>");
        createCreationForm(writer);
        writer.println("</br>");
        createQueryForm(writer);

        String query=Optional.ofNullable(req.getParameter("q"))
                .orElse("");
        List<User>userList=usersService.findByQuery(query);


        for (int i = 0; i < userList.size(); i++) {

            User user = userList.get(i);

            writer.println("<a href=\"" +req.getContextPath()+req.getServletPath()
                            +"/"+user.getId()+"\">");
            writer.println("<p>" + user.getFirstName() + " " + user.getLastName() +    "</p>");
            writer.println("<br/>");

            writer.println("</a>");
        }





    }

    private void createQueryForm(PrintWriter writer) {
        String form="<form action=\"\" method=\"get\">\n" +
                "    \n" +
                "   Wyszukaj <input type=\"text\" name=\"q\">\n" +
                "   <input type=\"submit\">\n" +
                "    \n" +
                "      \n" +
                "    \n" +
                "</form>";
        writer.println(form);
    }

    private void createCreationForm(PrintWriter writer) {
        String form="<form action=\"\" method=\"post\">\n" +
                " Imie   <input type=\"text\" name=\"firstName\">\n" +
                "    </br>\n" +
                " Nazwisko   <input type=\"text\" name=\"lastName\">\n" +
                "    </br>\n" +
                "  Wiek  <input type=\"text\" name=\"age\">\n" +
                "    </br>\n" +
                " Gender   <input type=\"text\" name=\"gender\">\n" +
                "    <input type=\"submit\">\n" +
                "    \n" +
                "    \n" +
                "</form>";
        writer.println(form);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int age =  Integer.valueOf(req.getParameter("age"));
        String gender = req.getParameter("gender");


        User user = new User(firstName,lastName,age,gender);
        usersService.save(user);
        resp.sendRedirect( req.getContextPath()+req.getServletPath() );

    }
}
