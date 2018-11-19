package com.sda.servlets.links;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LinkServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getPathInfo().substring(1));
        linksService.delete(id);
    }

    LinksService linksService;

    @Override
    public void init() throws ServletException {
        linksService = LinksService.instaceOf();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        reader.lines().forEach(x -> stringBuilder.append(x));
        String json = stringBuilder.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        Link link = objectMapper.readValue(json, Link.class);
        Integer id = Integer.valueOf(req.getPathInfo().substring(1));
        link.setId(id);
        linksService.save(link);
        PrintWriter writer = resp.getWriter();
        displayUser(writer, link);

    }

    private void displayUser(PrintWriter writer, Link link) {
        writer.println(link.getText());
    }


}
