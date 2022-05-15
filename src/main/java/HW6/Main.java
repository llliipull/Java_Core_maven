package HW6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.preemptive;

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
        //запись в базу
        try  {
            Connection connection = DriverManager.getConnection("jdbc:mysql://@localhost:3306/weather_db", "root", "qwerty");
            PreparedStatement statement = connection.prepareStatement("insert into weather (date, max_temp,min_temp) " +
                    "values(?, ?, ?)");
            for (DailyForecasts dailyForecast : dailyForecasts) {
                statement.setString(1, dailyForecast.getDate());
                statement.setInt(2, dailyForecast.getTemperature().getMaximum().getValue());
                statement.setInt(3,   dailyForecast.getTemperature().getMinimum().getValue());
                statement.addBatch();
                statement.executeBatch();
            }


        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}
