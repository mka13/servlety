package com.sda.servlets.links;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

public class LinksService {
    private static LinksService instance;
    public static LinksService instaceOf() {
        if (instance == null) {
            instance = new LinksService();
        }
        return instance;
    }

    private Integer id;
    List<Link> links;

    public LinksService() {
        this.links = new ArrayList<>();
        this.id = 1;

    }


    public void save(Link link) {
        if (link.getId() == null) {
            link.setId(id++);
            links.add(link);
        } else {
            links.stream()
                    .filter(x -> x.getId().equals(link.getId()))
                    .findAny()
                    .ifPresent(x -> {
                        x.setUrl(link.getUrl());
                        x.setText(link.getText());
                    });
        }

    }

    public List<Link> findAll() {

        return new ArrayList<>(links);

    }

    public void delete(Integer id) {
        this.links = links.stream()
                .filter(x -> !x.getId().equals(id))
                .collect(Collectors.toList());
    }


    public void findById(Integer id) {
        this.links.stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .ifPresent(x -> {
                    int i = links.indexOf(x);
                    links.remove(i);

                });
    }

}
