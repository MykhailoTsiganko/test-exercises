package com.wheelsup.rest.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestInfo {

    @JsonProperty("cta_text")
    private String ctaText;
    @JsonProperty("cta_url")
    private String ctaUrl;

    @JsonProperty("cta_text")
    public String getCtaText() {
        return ctaText;
    }

    @JsonProperty("cta_text")
    public void setCtaText(String ctaText) {
        this.ctaText = ctaText;
    }

    @JsonProperty("cta_url")
    public String getCtaUrl() {
        return ctaUrl;
    }

    @JsonProperty("cta_url")
    public void setCtaUrl(String ctaUrl) {
        this.ctaUrl = ctaUrl;
    }

}
