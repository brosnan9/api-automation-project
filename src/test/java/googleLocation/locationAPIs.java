package googleLocation;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class locationAPIs {

    private static String placeId;
    private static String updateResponse;
    private static String newAddress;
    @Test(priority = 1)
    public static void create(){
        String endpoint = "https://rahulshettyacademy.com/maps/api/place/add/json";
        var createResponse = given().queryParam("key", "qaclick123").body(payload.addLocationBody()).
                when().post(endpoint).
                then().log().body().assertThat().statusCode(200).extract().response().asString();

        placeId = convertToJsonObj.placeId(createResponse);

    }
    @Test(priority = 3)
    public static void read(){
        String endpoint = "http://rahulshettyacademy.com/maps/api/place/get/json";
        String readResponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeId).
                when().get(endpoint).
                then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(readResponse);

        String actualAddress = convertToJsonObj.address(readResponse);
        Assert.assertEquals(actualAddress, newAddress);

    }
    @Test(priority = 2)
    public static void update(){
        String endpoint = "https://rahulshettyacademy.com/maps/api/place/update/json";
        newAddress = "3J, Siaga Raya";
        updateResponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeId).body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").
                when().put(endpoint).
                then().statusCode(200).extract().response().asString();

    }
}
