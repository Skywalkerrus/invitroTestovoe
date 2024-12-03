package org.example.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.util.List;

public class APIStepDefinitions {
    @И("выполнить get запрос на url: {string} и сохранить ответ в переменную {string}")
    public void restAssGet(String url, String key, DataTable dataTable){
        List<String> data = dataTable.asList();
        String response1 = RestAssured.
                given()
                .header("User-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36")
                .cookie(data.get(0))
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().asString();
        System.setProperty(key, response1);
        System.out.println("Response: " + response1);
    }

    @И("сравнить значения из переменной: {string} со значениями из таблицы по jsonPath")
    public void equalValByJsonPath(String key, DataTable dataTable){
        String json1 = System.getProperty(key);
        JsonPath jsonPath = new JsonPath(json1);
        List<List<String>> rows = dataTable.asLists();
        for (List<String> lst : rows)
        {
            String val = jsonPath.getString(lst.get(0));
            System.out.println("val from save per->" + val);
            System.out.println("from datatable->" + lst.get(1));
            Assert.assertEquals(lst.get(1), val);
        }
    }
}
