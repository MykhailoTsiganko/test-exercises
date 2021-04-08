package com.wheelsup;

import com.wheelsup.ui.models.UserModel;
import org.testng.annotations.DataProvider;

public class UsersDataProvider {

    @DataProvider(name = "usersData")
    public Object[][] usersData() {
        var usersData = new UserModel();
        usersData.setFirstName("Mykhailo");
        usersData.setLastName("Tsyhanko");
        usersData.setEmail("misha@example.com");
        usersData.setPhone("1111111111");
        usersData.setCompanyName("example");
        usersData.setStreetAddress("Shevchenka 1 str");
        usersData.setCity("Lviv");
        usersData.setZip("11111");
        usersData.setState("Lvivska oblast");
        usersData.setCountry("Ukraine");
        usersData.setPrivateFlightNumber("0");
        usersData.setTravelWithPets("No");
        usersData.setHasSecondHome("No");
        usersData.setFlyType("Charter");
        usersData.setPreferableMembership("Wheels Up Core Membership");
        usersData.setHerdAboutUsSource("Airport");
        usersData.setSpecificQuestion("Question 1");

        return new Object[][]{{usersData}};
    }
}
