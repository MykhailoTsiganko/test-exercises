package com.wheelsup;

import com.wheelsup.rest.RestClient;
import com.wheelsup.rest.models.InitialData;
import com.wheelsup.rest.models.Keys;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise3RestApiTest {
    public static String INITIAL_DATA_REQUEST_PATH = "/initial-data.json";
    public static String INSTAGRAM_ACCOUNT_URL = "https://twitter.com/WheelsUp";
    public static String TWITTER_ACCOUNT_URL = "http://instagram.com/wheelsup8760";
    public static String EMAIL = "autotester.web@gmail.com";
    private Logger LOGGER = Logger.getLogger(Exercise3RestApiTest.class);
    private RestClient restClient;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        restClient = new RestClient();
    }

    @Test(description = "Verify Social Accounts")
    public void verifySocialAccountsTests() {
        verifySocialAccount(TWITTER_ACCOUNT_URL, INSTAGRAM_ACCOUNT_URL);

    }

    @Test(description = "Display Edited Kyes")
    public void displayEditedKeysTests() {
        displayEditedKeys();
    }

    private void verifySocialAccount(String twitter, String instagram) {
        var response = restClient.get(INITIAL_DATA_REQUEST_PATH, InitialData.class);
        response.expectingStatusCode(200);
        var entity = response.readEntity();
        assertThat(entity.getKeys().getTwitter()).isEqualTo(twitter);
        assertThat(entity.getKeys().getInstagram()).isEqualTo(instagram);
    }

    private void displayEditedKeys() {
        var response = restClient.get(INITIAL_DATA_REQUEST_PATH, InitialData.class);
        response.expectingStatusCode(200);
        var entity = response.readEntity();
        LOGGER.info("Total Keys Number :" + getFieldsSize(Keys.class, entity.getKeys()));

        entity.getKeys().setGoogleTagManager(null);
        entity.getKeys().setMapboxAccesstoken(null);
        entity.getKeys().setFooterDisclaimer(null);
        entity.getKeys().setEmail(EMAIL);
        LOGGER.info("Edited Keys:");
        LOGGER.info(entity.getKeys());
    }

    private <T> int getFieldsSize(Class<T> clazz, T object) {
        var fields = Arrays.asList(clazz.getDeclaredFields());
        return fields.stream().filter(field -> {
            field.setAccessible(true);
            try {
                return Objects.nonNull(field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }).collect(Collectors.toList()).size();
    }
}
