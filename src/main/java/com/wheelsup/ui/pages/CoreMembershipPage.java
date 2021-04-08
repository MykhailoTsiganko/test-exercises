package com.wheelsup.ui.pages;

import com.wheelsup.ui.utils.WebElementUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CoreMembershipPage extends BasePage {
    public static Logger LOGGER = Logger.getLogger(BasePage.class);

    @FindBy(xpath = "//h1[contains(text(),'Private')]")
    private WebElement privateAviationHeader;
    @FindBy(xpath = "//h1[contains(text(),'Learn more today')]")
    private WebElement learnMoreTodayHeader;
    @FindBy(xpath = "//h1[contains(text(),'Core Membership')]")
    private WebElement coreMembershipPageHeader;
    @FindBy(xpath = "//*[@id='ways_to_fly-component-3']//*[@class='title']")
    private List<WebElement> flightTypesTitleElements;
    @FindBy(css = "#FirstName-clone")
    private WebElement firstNameInput;
    @FindBy(css = "#LastName-clone")
    private WebElement lastNameInput;
    @FindBy(xpath = "//app-button//*[contains(text(),'CONTINUE')]")
    private WebElement continueButton;

    public void verifyOpened() {
        WebElementUtils.waitUntilDisplayed(coreMembershipPageHeader);
        assertThat(coreMembershipPageHeader.isDisplayed()).isTrue();
    }

    public void verifyPrivateAviationSectionVisible() {
        WebElementUtils.scrollToElement(privateAviationHeader);
        WebElementUtils.waitUntilDisplayed(privateAviationHeader);
        flightTypesTitleElements.forEach(WebElementUtils::waitUntilDisplayed);
        flightTypesTitleElements.forEach(webElement -> assertThat(webElement.isDisplayed()).isTrue());
        flightTypesTitleElements.forEach(webElement -> LOGGER.info(webElement.getText()));

    }

    public void verifyLearnMoreTodaySectionIsVisible() {
        WebElementUtils.scrollToElement(learnMoreTodayHeader);
        WebElementUtils.waitUntilDisplayed(learnMoreTodayHeader);
        assertThat(learnMoreTodayHeader.isDisplayed()).isTrue();
        assertThat(firstNameInput.isDisplayed()).isTrue();
    }

    public void fillInLearnMoreFormAndClickContinue(String firstName, String lastName) {
        firstNameInput.sendKeys(firstName + Keys.TAB);
        lastNameInput.sendKeys(lastName + Keys.TAB);
        WebElementUtils.waitUntilDisplayed(continueButton);
        continueButton.click();
    }
}
