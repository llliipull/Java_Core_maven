package HW6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.response.Response;


import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        RestAssured.baseURI = "http://dataservice.accuweather.com/";

        Response response = given()
                .when()
                .get("forecasts/v1/daily/5day/296181?apikey=hZz07lQbIOppQ2lVckyljP7cmHrTNG7q&metric=true");

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper
                .readTree(response.asPrettyString())
                .at("/DailyForecasts");

        List<DailyForecasts> dailyForecasts = new ArrayList<>();

        if(jsonNode.isArray()){
            for (JsonNode arrayItem : jsonNode) {
                dailyForecasts.add(objectMapper.convertValue(arrayItem,DailyForecasts.class));
            }

        }

        for (DailyForecasts dailyForecast : dailyForecasts) {
            System.out.println("В Ижевске " + dailyForecast.getDate() + " максимальная температура " +
                    dailyForecast.getTemperature().getMaximum().getValue() + " минимальная температура " +
                    dailyForecast.getTemperature().getMinimum().getValue());
        }

    }

}
