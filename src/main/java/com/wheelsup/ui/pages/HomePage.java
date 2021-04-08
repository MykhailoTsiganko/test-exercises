package com.wheelsup.ui.pages;

import com.wheelsup.ui.utils.WebElementUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;


public class HomePage extends BasePage {
    public static Logger LOGGER = Logger.getLogger(HomePage.class);

    @FindBy(xpath = "//h1[contains(text(),'Flying')]")
    private WebElement flyingPersonalizedHeader;
    @FindBy(xpath = "//h1[contains(text(),'Discover')]")
    private WebElement discoverPossibilitiesHeader;
    @FindBy(xpath = "//*[contains(text(),'Contact Us')]")
    private WebElement contactUsLabel;
    @FindBy(xpath = "//*[contains(text(),'Contact Us')]")
    private WebElement findUsLabel;
    @FindBy(xpath = "//*[contains(text(),'Contact Us')]/following::*[contains(@href,'tel')]")
    private WebElement telephoneElement;
    @FindBy(xpath = "//*[contains(text(),'Contact Us')]/following::*[contains(@href,'mailto')]")
    private WebElement emailElement;
    @FindBy(xpath = "//*[contains(text(),'Find Us')]/following::*[@class='base-label']")
    private WebElement addressElement;
    @FindBy(xpath = "//*[@class='menu-item']//*[contains(text(),'Fly')]")
    private WebElement flyMenuItemElement;
    @FindBy(xpath = "//*[contains(@class,'menu-item-container')]//*[contains(text(),'CORE MEMBERSHI')]")
    private WebElement coreMembershipSubMenuItemElement;

    public void verifyFlyingPersonalizedHeaderVisible() {
        WebElementUtils.waitUntilDisplayed(flyingPersonalizedHeader);
        assertThat(flyingPersonalizedHeader.isDisplayed()).isTrue();
        LOGGER.info(flyingPersonalizedHeader.getText());

    }

    public void verifyDiscoverPossibilitiesHeaderVisible() {
        WebElementUtils.scrollToElement(discoverPossibilitiesHeader);
        WebElementUtils.waitUntilDisplayed(flyingPersonalizedHeader);
        assertThat(discoverPossibilitiesHeader.isDisplayed()).isTrue();
        LOGGER.info(discoverPossibilitiesHeader.getText());

    }

    public void verifyContactUsSectionPresent() {
        WebElementUtils.scrollToElement(contactUsLabel);
        WebElementUtils.waitUntilDisplayed(contactUsLabel);
        assertThat(contactUsLabel.isDisplayed()).isTrue();
        assertThat(telephoneElement.isDisplayed()).isTrue();
        assertThat(emailElement.isDisplayed()).isTrue();
        LOGGER.info(telephoneElement.getText());
        LOGGER.info(emailElement.getText());
    }

    public void verifyFindUsSectionPresent() {
        WebElementUtils.scrollToElement(findUsLabel);
        WebElementUtils.waitUntilDisplayed(findUsLabel);
        assertThat(findUsLabel.isDisplayed()).isTrue();
        assertThat(addressElement.isDisplayed()).isTrue();
        LOGGER.info(addressElement.getText());
    }

    public void openCoreMembershipPage() {
        WebElementUtils.scrollToElement(flyingPersonalizedHeader);
        WebElementUtils.waitUntilDisplayed(flyingPersonalizedHeader);
        WebElementUtils.waitUntilDisplayed(flyMenuItemElement);
        flyMenuItemElement.click();
        WebElementUtils.waitUntilDisplayed(coreMembershipSubMenuItemElement);
        coreMembershipSubMenuItemElement.click();
    }
}
