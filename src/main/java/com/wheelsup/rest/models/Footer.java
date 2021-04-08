package com.wheelsup.rest.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "subitems"
})
public class Footer {

    @JsonProperty("title")
    private String title;
    @JsonProperty("subitems")
    private List<Subitem> subitems = null;

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("subitems")
    public List<Subitem> getSubitems() {
        return subitems;
    }

    @JsonProperty("subitems")
    public void setSubitems(List<Subitem> subitems) {
        this.subitems = subitems;
    }

}
