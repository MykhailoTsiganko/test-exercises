package com.wheelsup;

import com.wheelsup.ui.actions.CommonActions;
import com.wheelsup.ui.actions.NavigationAction;
import com.wheelsup.ui.engine.DriverProvider;
import com.wheelsup.ui.models.UserModel;
import com.wheelsup.ui.pages.CoreMembershipPage;
import com.wheelsup.ui.pages.HomePage;
import com.wheelsup.ui.pages.LearnMoreFormPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise2WebUiTest {

    private HomePage homePage;
    private CoreMembershipPage coreMembershipPage;
    private NavigationAction navigationAction;
    private LearnMoreFormPage learnMoreFormPage;
    private CommonActions commonActions;

    @BeforeMethod(alwaysRun = true)
    public void initPages() {
        homePage = new HomePage();
        navigationAction = new NavigationAction();
        coreMembershipPage = new CoreMembershipPage();
        commonActions = new CommonActions();
        learnMoreFormPage = new LearnMoreFormPage();
    }

    @Test(dataProviderClass = UsersDataProvider.class, dataProvider = "usersData", invocationCount = 5)
    public void verify(UserModel user) {
        navigationAction.openHomePage();
        homePage.verifyFlyingPersonalizedHeaderVisible();
        homePage.verifyDiscoverPossibilitiesHeaderVisible();
        homePage.verifyContactUsSectionPresent();
        homePage.verifyFindUsSectionPresent();
        homePage.openCoreMembershipPage();
        coreMembershipPage.verifyOpened();
        coreMembershipPage.verifyPrivateAviationSectionVisible();
        coreMembershipPage.verifyLearnMoreTodaySectionIsVisible();
        coreMembershipPage.fillInLearnMoreFormAndClickContinue(user.getFirstName(), user.getLastName());
        commonActions.verifyBrowserUrlContainsPath("request-info");
        learnMoreFormPage.verifyOpened();
        learnMoreFormPage.fillInnAllFieldsAndClose(user);
        coreMembershipPage.verifyOpened();
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        DriverProvider.quit();
    }
}
