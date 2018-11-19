package com.sda.servlets.users;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {


    public UsersService usersService;
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getPathInfo().substring(1)); //--->wildcard
            usersService.delete(id);

    }
    @Override
    public void init() throws ServletException {
        this.usersService=UsersService.instaceOf();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder=new StringBuilder();
        reader.lines().forEach(e->stringBuilder.append(e));
        String json = stringBuilder.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        User user=objectMapper.readValue(json,User.class);
        Integer id = Integer.valueOf(req.getPathInfo().substring(1));
        user.setId(id);
        usersService.save(user);



    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        PrintWriter writer=resp.getWriter();
        Integer id=Integer.valueOf(pathInfo.substring(1));
        try{
        User user = usersService.findById(id);
        displayUser(user,resp.getWriter());}
        catch (UserNotFoundException e){
            writer.println(e.getMessage());
            resp.setStatus(404);
        }

    }

private void displayUser(User user, PrintWriter writer){
        writer.println(user.getFirstName() + " " +user.getLastName());
        writer.println("Gender: " + user.getGender());
        writer.println("Age: " + user.getAge());


}
}
