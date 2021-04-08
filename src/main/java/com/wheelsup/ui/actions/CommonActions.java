package com.wheelsup.ui.actions;

import com.wheelsup.ui.engine.DriverProvider;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonActions {
    public void verifyBrowserUrlContainsPath(String path) {
        assertThat(DriverProvider.getDriver().getCurrentUrl()).contains(path);
    }
}
