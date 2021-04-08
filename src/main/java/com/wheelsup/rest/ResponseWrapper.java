package com.wheelsup.rest;

import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertNotNull;

public class ResponseWrapper<T> {
    private final Response response;
    private final Class<T> responseClass;

    ResponseWrapper(Response response, Class<T> responseClass) {
        this.response = response;
        this.responseClass = responseClass;
    }

    public Response getResponse() {
        assertNotNull(response);
        return response;
    }

    public T readEntity() {
        return response.getBody().as(responseClass);
    }

    public ResponseWrapper<T> expectingStatusCode(int statusCode) {
        assertThat("Response code differs", response.getStatusCode(), is(statusCode));
        return this;
    }

}
