package HW6;

import io.restassured.RestAssured;
import io.restassured.response.Response;



import static io.restassured.RestAssured.given;

public class Main {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://dataservice.accuweather.com/";

        Response response = given().when().get("forecasts/v1/daily/5day/296181?apikey=hZz07lQbIOppQ2lVckyljP7cmHrTNG7q");

        if(response.getStatusCode() == 200){
            System.out.println("Status code is 200");
        }

        System.out.println(response.asPrettyString());


    }
}
