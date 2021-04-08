package com.wheelsup.ui.engine;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import com.wheelsup.core.EnvProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverProvider {
    private static final ThreadLocal<WebDriver> DRIVER_INSTANCE = new ThreadLocal<>();

    public static Map<String, Supplier<WebDriver>> driverMap = ImmutableMap.<String, Supplier<WebDriver>> builder()
            .put("chrome", () -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }).put("firefox", () -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            })
            .build();

    public static WebDriver getDriver() {
        if (Objects.isNull(DRIVER_INSTANCE.get())) {
            DRIVER_INSTANCE.set(driverMap.get(EnvProperties.getEnvProperties().getBrowserName()).get());
            DRIVER_INSTANCE.get().manage().window().maximize();
            DRIVER_INSTANCE.get().manage().timeouts()
                    .implicitlyWait(EnvProperties.getEnvProperties().getDriverTimeout(), TimeUnit.SECONDS);
            DRIVER_INSTANCE.get().manage().timeouts()
                    .pageLoadTimeout(EnvProperties.getEnvProperties().getDriverTimeout(), TimeUnit.SECONDS);
        }
        return DRIVER_INSTANCE.get();
    }

    public static void quit() {
        DRIVER_INSTANCE.get().quit();
        DRIVER_INSTANCE.set(null);
    }
}
