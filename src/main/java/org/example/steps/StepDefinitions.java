package org.example.steps;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Text;
import io.cucumber.java.en.Given;
import io.cucumber.java.ru.И;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.codeborne.selenide.Selenide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class StepDefinitions {

    @И("сохранить переменную с именем: {string} и значением: {string}")
    public void saveValueInSystem(String key, String value){
        System.setProperty(key, value);
    }

    @И("взять значение поля {string} и сохранить в переменную с именем: {string}")
    public void getValueAndSaveInSystem(String xPath, String key){
        SelenideElement element = $(By.xpath(xPath));
        System.setProperty(key, Objects.requireNonNull(element.getValue()));
    }

    @И("взять значение из сохраненной переменной: {string} и сравнить с: {string}")
    public void assertValueFromSaveInSystem(String key, String value){
        System.out.println("sys value->" + System.getProperty(key) + "<-");
        System.out.println("usr value->" + value + "<-");
        Assert.assertEquals(System.getProperty(key), value);
    }

    @И("^ожидать секунд (\\d+)$")
    public void waitMilisec(Integer milisec){
        sleep(milisec * 1000);
    }

    @И("открыть страницу {string}")
    public void openPage(String url){
        open(url);
    }

    @И("выполнено нажатие на элемент по тексту {string}")
    public void elementClickByText(String text){
        $(By.xpath("//*[text()='"+ text + "']")).click();
    }

    @И("выполнено нажатие на элемент с xpath: {string}")
    public void elementClickByXpath(String xPath){
        $(By.xpath(xPath)).shouldBe(visible).click();
    }

    @И("поле с xpath: {string} появилось")
    public void fieldIsVisible(String xPath){
        SelenideElement element = $(By.xpath(xPath));
        assertTrue("элемент не видно", element.shouldBe(visible).is(visible));
    }

    @И("в поле с xpath: {string} введено значение {string}")
    public void elementEnterValByXpath(String xPath, String str){
        SelenideElement element = $(By.xpath(xPath));
        element.sendKeys(str);
    }

    @И("в поле с xpath: {string} значение текста равно {string}")
    public void elementTextAssertByXpath(String xPath, String str){
        SelenideElement element = $(By.xpath(xPath));
        String val = element.getText();
        System.out.println("val->" + val + "\nstr->" + str);
        Assert.assertEquals(str, val);
    }

    @И("сравнить значение из поля с xpath: {string} со значением: {string}")
    public void elementValAssertByXpath(String xPath, String str){
        SelenideElement element = $(By.xpath(xPath));
        String val = element.getValue();
        System.out.println("val->" + val + "\nstr->" + str);
        Assert.assertEquals(str, val);
    }

    @И("прокилик всех элементов панели мед услуги {string}")
    public void secStep(String rootDiv){
        int i = 1;
        int itemScore = $$(By.xpath(rootDiv)).size();
        while (i <= itemScore) {
        //for (SelenideElement el : itemsList) {
            try {
                SelenideElement el = $(By.xpath(rootDiv + "[" + i + "]"));
                System.out.println("Clicking on menu item: " + el.getText());
                String parentText = el.getText();
                SelenideElement parentEl = $(By.xpath("//li/a[text()='" + parentText + "']"));
                parentEl.click();
                ElementsCollection elemets2 = $$(By.xpath(".//li/a[text()= '" + parentText + "']/../div/ul/li"));
                int s2 = elemets2.size();
                int j = 1;
                while (j <= s2)
                {
                    System.out.println("parentText_>" + parentText);
                    $(By.xpath("//a[text()='"+ parentText + "']/following-sibling::div/ul/li[" + j + "]")).click();
                    j++;
                }
            } catch (Exception e) {
                System.out.println("Failed to click on item: " + e.getMessage());
            }
            i++;
        }
    }
}
