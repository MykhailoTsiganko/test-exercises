package com.wheelsup.ui.actions;

import com.wheelsup.core.EnvProperties;
import com.wheelsup.ui.engine.DriverProvider;

public class NavigationAction {
    public void navigateTo(String url) {
        DriverProvider.getDriver().navigate().to(url);
    }

    public void openHomePage() {
        DriverProvider.getDriver().get(EnvProperties.getEnvProperties().getBaseUrl());
    }
}
