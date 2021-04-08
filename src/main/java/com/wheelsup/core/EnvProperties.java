package com.wheelsup.core;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class EnvProperties {
    private static EnvProperties INSTANCE;

    @Value(name = "base.url")
    private String baseUrl;
    @Value(name = "browser.name")
    private String browserName;
    @Value(name = "driver.timeout")
    private String driverTimeout;
    @Value(name = "scroll.wait.in.milliseconds")
    private String scrollWait;

    private EnvProperties() {
        processProperties();
    }

    public static EnvProperties getEnvProperties() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new EnvProperties();
        }
        return INSTANCE;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBrowserName() {
        return browserName;
    }

    public int getDriverTimeout() {
        return Integer.parseInt(driverTimeout);
    }

    public Integer getScrollWait() {
        return Integer.parseInt(scrollWait);
    }

    private void processProperties() {
        Properties properties = new Properties();
        try {
            if (StringUtils.isEmpty(System.getProperty("env"))) {

                properties.load(new FileInputStream("src/test/resources/test-env.properties"));

            } else {
                properties.load(new FileInputStream(format("src/test/resources/%s-env.properties", System.getProperty("env"))));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var fields = Arrays.asList(EnvProperties.class.getDeclaredFields())
                .stream().filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toList());
        fields.forEach(field -> {
            field.setAccessible(true);
            var annotation = field.getAnnotation(Value.class);
            try {
                if (StringUtils.isEmpty(System.getProperty(annotation.name()))) {
                    field.set(this, properties.get(annotation.name()));
                } else {
                    field.set(this, System.getProperty(annotation.name()));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
