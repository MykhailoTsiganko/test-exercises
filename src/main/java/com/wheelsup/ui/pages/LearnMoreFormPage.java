package com.wheelsup.ui.pages;

import com.wheelsup.core.EnvProperties;
import com.wheelsup.ui.engine.DriverProvider;
import com.wheelsup.ui.models.UserModel;
import com.wheelsup.ui.utils.WebElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static com.wheelsup.ui.utils.WebElementUtils.waitUntilClickable;
import static com.wheelsup.ui.utils.WebElementUtils.waitUntilScrollOccurs;
import static java.lang.String.format;

public class LearnMoreFormPage extends BasePage {

    @FindBy(css = "#FirstName-clone")
    private WebElement firstNameInput;
    @FindBy(css = "#LastName-clone")
    private WebElement lastNameInput;
    @FindBy(css = "#Email-clone")
    private WebElement emailInput;
    @FindBy(css = "#Phone-clone")
    private WebElement phoneNumberInput;
    @FindBy(css = "#Company__c-clone")
    private WebElement companyInput;
    @FindBy(css = "#Address-clone")
    private WebElement streetAddressInput;
    @FindBy(css = "#City-clone")
    private WebElement cityInput;
    @FindBy(css = "#PostalCode-clone")
    private WebElement zipInput;
    @FindBy(css = "#State-clone")
    private WebElement stateInput;
    @FindBy(css = "#Country-clone")
    private WebElement countryInput;
    @FindBy(xpath = "//*[contains(@id,'How_Many_Private_Flights_Per_Year')]//*[@class='label']")
    private WebElement privateFlightsNumberSelect;
    @FindBy(xpath = "//*[contains(@id,'How_Many_Private_Flights_Per_Year')]//*[contains(@class,'icon success normal')]")
    private WebElement privateFlightsNumberSelectSuccessIcon;
    @FindBy(xpath = "//*[contains(@id,'Do_you_own_or_travel_to_a_second_home')]//*[@class='label']")
    private WebElement secondHomeSelect;
    @FindBy(xpath = "//*[contains(@id,'Do_you_own_or_travel_to_a_second_home')]//*[contains(@class,'icon success normal')]")
    private WebElement secondHomeSelectSuccessIcon;
    @FindBy(xpath = "//*[contains(@id,'eadSourceWebList')]//*[@class='label']")
    private WebElement heardAboutUsSelect;
    @FindBy(xpath = "//*[contains(@id,'eadSourceWebList')]//*[contains(@class,'icon success normal')]")
    private WebElement heardAboutUsSelectSuccessIcon;
    @FindBy(xpath = "//*[contains(@id,'travel_with_pets')]")
    private WebElement travelWithPetsHeader;
    @FindBy(xpath = "//*[contains(@id,'currently_Travel')]")
    private WebElement currentlyFlyLabel;
    @FindBy(xpath = "//*[contains(@id,'Product_Interest')]")
    private WebElement membershipFlyLabel;
    @FindBy(css = "#WebFormComment__c-clone .textarea-box")
    private WebElement specificQuestionForm;
    @FindBy(css = ".right-content .close .icon-close")
    private WebElement closeFormButton;
    private String followingSelectOption = "./following::*[contains(text(),'%s')]";
    private By followingDropDown = By.xpath("./following::*[contains(@class,'dropdown-box-list')]");
    private String innerRadioButton = ".//*[contains(@value,'%s')]/ancestor::*[@class='containerline']";


    public void verifyOpened() {
        WebElementUtils.waitUntilDisplayed(emailInput);
    }

    public void fillInnAllFieldsAndClose(UserModel user) {
        setEmailInput(user.getEmail());
        setPhoneNumberInput(user.getPhone());
        setCompanyInput(user.getCompanyName());
        setStreetAddressInput(user.getStreetAddress());
        setCityInput(user.getCity());
        setZipInput(user.getZip());
        setStateInput(user.getCity());
        setCountryInput(user.getCountry());

        chooseFlightNumberOption(user.getPrivateFlightNumber());
        chooseTraverWithPets(user.getHasSecondHome());
        chooseSecondHomeOption(user.getHasSecondHome());
        chooseCurrentFlyType(user.getFlyType());
        chooseMembershipFlyType(user.getPreferableMembership());
        chooseHeardAboutUsOption(user.getHerdAboutUsSource());
        setSpecificQuestionForm(user.getSpecificQuestion());
        closeFormButton.click();
    }

    private void chooseCurrentFlyType(String flyType) {
        chooseCheckBoxAnswerOption(currentlyFlyLabel, flyType);
    }

    private void chooseMembershipFlyType(String flyType) {
        chooseCheckBoxAnswerOption(membershipFlyLabel, flyType);
    }

    private void chooseFlightNumberOption(String privateFlightNumber) {
        chooseSelectOption(privateFlightsNumberSelect, privateFlightsNumberSelectSuccessIcon, privateFlightNumber);
    }

    private void chooseHeardAboutUsOption(String herdAboutUs) {
        chooseSelectOption(heardAboutUsSelect, heardAboutUsSelectSuccessIcon, herdAboutUs);
    }

    private void chooseSelectOption(WebElement selectElement, WebElement successIcon, String privateFlightNumber) {
        WebElementUtils.scrollToElementInCenter(selectElement);
        WebElementUtils.waitUntilClickable(selectElement);
        WebElementUtils.saveClick(() -> selectElement);

        WebElementUtils.waitUntilChildVisible(selectElement,
                followingDropDown);

        var optionToSelect = By.xpath(format(followingSelectOption, privateFlightNumber));

        WebElementUtils.waitUntilChildClickable(selectElement,
                optionToSelect);
        WebElementUtils.saveClick(() -> selectElement.findElement(optionToSelect));
        WebElementUtils.waitUntilDisplayed(successIcon);
    }

    private void chooseCheckBoxAnswerOption(WebElement select, String optionValue) {
        WebElementUtils.scrollToElementInCenter(select);
        WebElementUtils.waitUntilDisplayed(select);
        var checkboxXpath = By.xpath(format(innerRadioButton, optionValue));
        WebElementUtils.waitUntilChildVisible(select, checkboxXpath);
        select.findElement(checkboxXpath).click();
    }

    private void chooseRadioButtonAnswerOption(WebElement travelWithPetsHeader, String traverWithPets) {
        WebElementUtils.scrollToElementInCenter(travelWithPetsHeader);
        var valueRadioButtonXpath = By.xpath(format(innerRadioButton, traverWithPets));
        WebElementUtils.waitUntilChildVisible(travelWithPetsHeader, valueRadioButtonXpath);
        travelWithPetsHeader.findElement(valueRadioButtonXpath).click();
    }

    public void chooseTraverWithPets(String value) {
        chooseRadioButtonAnswerOption(travelWithPetsHeader, value);
    }


    public void chooseSecondHomeOption(String secondHome) {
        chooseSelectOption(secondHomeSelect, secondHomeSelectSuccessIcon, secondHome);
    }

    public void setEmailInput(String email) {
        waitUntilScrollOccurs();
        waitUntilClickable(emailInput);
        var pause = Duration.ofMillis(EnvProperties.getEnvProperties().getScrollWait());
        new Actions(DriverProvider.getDriver())
                .pause(pause)
                .moveToElement(emailInput).click()
                .pause(pause)
                .sendKeys(email + Keys.TAB)
                .perform();
    }

    public void setPhoneNumberInput(String phone) {
        setInput(phoneNumberInput, phone + Keys.TAB);
    }

    public void setCompanyInput(String company) {
        setInput(companyInput, company + Keys.TAB);
    }

    public void setStreetAddressInput(String streetAddress) {
        setInput(streetAddressInput, streetAddress + Keys.TAB);
    }

    public void setCityInput(String city) {
        setInput(cityInput, city + Keys.TAB);
    }

    public void setZipInput(String zip) {
        setInput(zipInput, zip + Keys.TAB);
    }

    public void setStateInput(String state) {
        setInput(stateInput, state + Keys.TAB);

    }

    public void setCountryInput(String country) {
        setInput(countryInput, country + Keys.TAB);
        waitUntilScrollOccurs();
    }

    public void setSpecificQuestionForm(String specificQuestion) {
        WebElementUtils.scrollToElementInCenter(specificQuestionForm);
        specificQuestionForm.sendKeys(specificQuestion + Keys.TAB);
    }

    public void setInput(WebElement input, CharSequence... value) {
        if (!input.isDisplayed()) {
            WebElementUtils.scrollToElementInCenter(input);
            WebElementUtils.waitUntilDisplayed(input);
        }
        input.sendKeys(value);
        WebElementUtils.waitUntilDisplayed(input.findElement(
                By.xpath("./ancestor::*[@class='Input']//*[contains(@class,'icon success normal')]")));
    }
}
