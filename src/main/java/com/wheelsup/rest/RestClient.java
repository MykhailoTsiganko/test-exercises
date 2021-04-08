package com.wheelsup.rest;

import com.wheelsup.core.EnvProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class RestClient {
    private RequestSpecification requestSpecification;

    public RestClient() {
        this.requestSpecification = new RequestSpecBuilder()
                .setUrlEncodingEnabled(false)
                .setBaseUri(EnvProperties.getEnvProperties().getBaseUrl())
                .setBasePath("_mock_")
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.BODY)
                .build();
    }

    private void logResponse(Response response) {
        response.then().log().all();
    }

    public <F> ResponseWrapper<F> get(String path, Class<F> responseClass) {
        Response response = given().spec(requestSpecification).get(path);
        logResponse(response);
        return new ResponseWrapper<>(response, responseClass);
    }

}