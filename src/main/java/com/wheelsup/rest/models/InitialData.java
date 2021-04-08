package com.wheelsup.rest.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InitialData {

    @JsonProperty("header")
    private List<Header> header = null;
    @JsonProperty("footer")
    private List<Footer> footer = null;
    @JsonProperty("keys")
    private Keys keys;
    @JsonProperty("redirects")
    private Redirects redirects;
    @JsonProperty("request-info")
    private RequestInfo requestInfo;

    @JsonProperty("header")
    public List<Header> getHeader() {
        return header;
    }

    @JsonProperty("header")
    public void setHeader(List<Header> header) {
        this.header = header;
    }

    @JsonProperty("footer")
    public List<Footer> getFooter() {
        return footer;
    }

    @JsonProperty("footer")
    public void setFooter(List<Footer> footer) {
        this.footer = footer;
    }

    @JsonProperty("keys")
    public Keys getKeys() {
        return keys;
    }

    @JsonProperty("keys")
    public void setKeys(Keys keys) {
        this.keys = keys;
    }

    @JsonProperty("redirects")
    public Redirects getRedirects() {
        return redirects;
    }

    @JsonProperty("redirects")
    public void setRedirects(Redirects redirects) {
        this.redirects = redirects;
    }

    @JsonProperty("request-info")
    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    @JsonProperty("request-info")
    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

}
