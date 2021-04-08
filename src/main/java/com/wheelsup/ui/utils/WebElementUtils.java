package com.wheelsup.ui.utils;

import com.wheelsup.core.EnvProperties;
import com.wheelsup.ui.engine.DriverProvider;
import org.awaitility.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.awaitility.Awaitility.await;

public class WebElementUtils {

    public static void scrollToElement(WebElement webElement) {
        ((JavascriptExecutor) DriverProvider.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", webElement);
        waitUntilScrollOccurs();
    }


    public static void scrollToElementInCenter(WebElement webElement) {
        String scrollElementIntoMiddle =
                "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                        + "var elementTop = arguments[0].getBoundingClientRect().top;"
                        + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript(scrollElementIntoMiddle, webElement);
        waitUntilScrollOccurs();
    }

    public static void waitUntilDisplayed(WebElement webElement) {
        waitUntilDisplayed(webElement, EnvProperties.getEnvProperties().getDriverTimeout());
    }

    public static void waitUntilClickable(WebElement webElement) {
        waitUntilDisplayed(webElement, EnvProperties.getEnvProperties().getDriverTimeout());
        new WebDriverWait(DriverProvider.getDriver(),
                EnvProperties.getEnvProperties().getDriverTimeout())
                .ignoring(WebDriverException.class)
                .pollingEvery(java.time.Duration.ofSeconds(1))
                .until(ExpectedConditions.and(ExpectedConditions.visibilityOf(webElement),
                        ExpectedConditions.elementToBeClickable(webElement)));
    }

    private static boolean performClick(Supplier<WebElement> webElement) {
        try {
            webElement.get().click();
            return true;
        } catch (ElementClickInterceptedException e) {
            return false;
        }
    }

    public static void  saveClick(Supplier<WebElement> webElement) {
        for (int i = 0; i < 5; i++) {
            if (performClick(webElement)) {
                break;
            }
        }
    }

    public static void waitUntilDisplayed(WebElement webElement, int timeOutInSeconds) {
        new WebDriverWait(DriverProvider.getDriver(), java.time.Duration.ofSeconds(timeOutInSeconds).getSeconds())
                .ignoring(WebDriverException.class)
                .pollingEvery(java.time.Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitUntilChildVisible(WebElement webElement, By childLocator) {
        new WebDriverWait(DriverProvider.getDriver(), EnvProperties.getEnvProperties().getDriverTimeout())
                .ignoring(WebDriverException.class)
                .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(webElement, childLocator));
    }

    public static void waitUntilChildClickable(WebElement webElement, By childLocator) {
        new WebDriverWait(DriverProvider.getDriver(), EnvProperties.getEnvProperties().getDriverTimeout())
                .ignoring(WebDriverException.class)
                .until(ExpectedConditions.and(ExpectedConditions.visibilityOfNestedElementsLocatedBy(webElement, childLocator),
                        ExpectedConditions.elementToBeClickable(webElement.findElement(childLocator))));
    }

    public static void waitUntilChildElementSelected(WebElement webElement, By childLocator) {
        new WebDriverWait(DriverProvider.getDriver(), EnvProperties.getEnvProperties().getDriverTimeout())
                .ignoring(WebDriverException.class)
                .until(driver -> webElement.findElement(childLocator).isSelected());
    }

    public static void waitUntilChildCssValue(WebElement webElement, By xpath, String attribute, String value) {
        new WebDriverWait(DriverProvider.getDriver(), EnvProperties.getEnvProperties().getDriverTimeout())
                .ignoring(WebDriverException.class)
                .until(driver -> webElement.findElement(xpath).getCssValue(attribute).equals(value));
    }

    public static void waitUntilScrollOccurs() {
        var timeout = EnvProperties.getEnvProperties().getScrollWait();
        long time = System.currentTimeMillis();
        await().atMost(new Duration(timeout + 500, TimeUnit.MILLISECONDS))
                .until(() -> System.currentTimeMillis() - time > timeout);
    }
}
