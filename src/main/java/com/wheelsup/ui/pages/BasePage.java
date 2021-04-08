package com.wheelsup.ui.pages;

import com.wheelsup.ui.engine.DriverProvider;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }
}
