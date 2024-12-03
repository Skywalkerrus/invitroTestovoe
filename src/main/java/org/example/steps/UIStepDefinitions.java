package org.example.steps;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.И;
import org.example.enums.HardVal;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UIStepDefinitions {

    @И("выбираю раздел {string}")
    public void selectSection(String section) {
        if (!HardVal.isValid(section)) {
            throw new IllegalArgumentException("Допустимые значения: Пациентам, Врачам, Франчайзинг, Корпоративным клиентам, Прессе. Получено: " + section);
        }
        $(byXpath(".//div[@id='buttonOpenPopupTargetSTATICSTRINGFORID']")).shouldBe(visible).click();
        $(byText(section)).shouldBe(visible).click();
    }

    @И("сохранить переменную с именем: {string} и значением: {string}")
    public void saveValueInSystem(String key, String value){
        System.setProperty(key, value);
    }

    @И("взять значение поля с xpath {string} и сохранить в переменную с именем: {string}")
    public void getValueAndSaveInSystem(String xPath, String key){
        SelenideElement element = $(By.xpath(xPath));
        System.setProperty(key, Objects.requireNonNull(element.getValue()));
    }

    @И("взять текст поля с xpath {string} очистить от мусора {string} и сохранить в переменную с именем: {string}")
    public void getTextCleanAndSaveInSystem(String xPath, String trash, String key){
        SelenideElement element = $(By.xpath(xPath));
        String text = element.getText();
        System.setProperty(key, Objects.requireNonNull(cleanUpText(text, trash)));
    }

    // 4 задание
    @И("^взять значение из сохраненной переменной: \"(.*)\" и сравнить в цифре с значением: (\\d+)$")
    public void assertValueFromSaveInSystem23(String key, Integer val2){
        System.out.println("sys value->" + System.getProperty(key) + "<-");
        System.out.println("equal value->" + val2 + "<-");
        int val1 = Integer.parseInt(System.getProperty(key));

        assertAll("Проверка значений",
                () -> {
                    if (val1 > val2) {
                        System.out.println("val1->" + val1 + " больше" + val2 + "<-");
                    } else {
                        System.out.println("val1->" + val1 + " меньше " + val2 + "<-");
                    }
                },
                () -> {
                    if (val1 == val2) {
                        System.out.println("->" + val1 + " равно " + val2 + "<-");
                    } else {
                        System.out.println("->" + val1 + " не равно " + val2 + "<-");
                    }
                },
                () -> {
                    if (val2 > val1) {
                        System.out.println("val1->" + val2 + " больше " + val1 + "<-");
                    } else {
                        System.out.println("val1->" + val2 + " меньше " + val1 + "<-");
                    }
                }

        );
    }

    // 4 задание
    @И("взять значение из сохраненной переменной: {string} и сравнить в цифре с другой сохраненной переменной: {string}")
    public void assertValueFromSaveInSystem2(String key, String key2){
        System.out.println("sys value->" + System.getProperty(key) + "<-");
        System.out.println("usr value->" + System.getProperty(key2) + "<-");
        int val1 = Integer.parseInt(System.getProperty(key));
        int val2 = Integer.parseInt(System.getProperty(key2));

        assertAll("Проверка значений",
                () -> {
                    if (val1 == val2) {
                        throw new AssertionError("Значение равны, ошибка!"); // Вызываем ошибку, если значение равно 10000
                        }
                },
                () -> {
                    if (val1 > val2) {
                        System.out.println("val1->" + val1 + " больше " + val2 + "<-");
                    } else {
                        System.out.println("val1->" + val1 + " меньше " + val2 + "<-");
                    }
                }
        );
    }

    private String cleanUpText(String text, String targetsForClean) {
        StringBuilder cleanText = new StringBuilder(text);
        System.out.println("text before clean->" + text + "<-");

        String[] strTarg = targetsForClean.split(",");
        for (String target : strTarg) {
            System.out.println("target: " + target);
            int index;
            while ((index = cleanText.indexOf(target)) != -1) {
                cleanText.delete(index, index + target.length());
            }
        }
        String newStr = cleanText.toString();
        System.out.println("text after cleaning->" + newStr);
        return newStr;
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
        getAndCheckWebDriver().manage().window().maximize();
    }

    @И("выполнено нажатие на элемент по тексту {string}")
    public void elementClickByText(String text){
        $(By.xpath("//*[text()='"+ text + "']")).click();
    }

    @И("выполнено нажатие на элемент с xpath: {string}")
    public void elementClickByXpath(String xPath){
        SelenideElement element = $(By.xpath(xPath));
        element.shouldBe(visible);
        executeJavaScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    @И("поле с xpath: {string} появилось")
    public void fieldIsVisible(String xPath){
        SelenideElement element = $(By.xpath(xPath));
        assertTrue("элемент не видно", element.shouldBe(visible).is(visible));
    }

    @И("ввести код анализа: {string} в поле поиска") //5 задание
    public void enterCodeAnalyzToField(String value){
        SelenideElement element = $(By.xpath(".//input[@class='search__input form-header-search_input']"));
        element.sendKeys(value);
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

    @И("прокилик всех элементов панели мед услуги {string}") //1 задание
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
