package com.sda.servlets.links;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {
    private String url;
    private String text;
    private Integer id;

    public Link(String url, String text) {
 this.url=url;
 this.text=text;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @JsonCreator
    public Link(@JsonProperty ("url")String url,@JsonProperty ("text") String text, @JsonProperty ("id")Integer id) {
         this.url = url;
         this.text = text;
          this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setText(String text) {
        this.text = text;
    }
}
