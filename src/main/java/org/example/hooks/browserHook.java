package org.example.hooks;

import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;

public class browserHook {
    @Before("@browserMax")
    public void openPage(){
        open("https://www.invitro.ru/moscow/radiology/");
        getAndCheckWebDriver().manage().window().maximize();
    }
}
