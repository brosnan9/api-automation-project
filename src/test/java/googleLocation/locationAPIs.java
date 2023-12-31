package googleLocation;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class locationAPIs {

    private static String placeId;
    private static String newAddress;
    @Test(priority = 1)
    public static void create(){

        String endpoint = "https://rahulshettyacademy.com/maps/api/place/add/json";
        var createResponse = given().queryParam("key", "qaclick123").body(payload.addLocationBody()).
                when().post(endpoint).
                then().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = convertToJsonObj.obj(createResponse);
        placeId = js.getString("place_id");

    }
    @Test(priority = 3)
    public static void read(){
        String endpoint = "http://rahulshettyacademy.com/maps/api/place/get/json";
        String readResponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeId).
                when().get(endpoint).
                then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(readResponse);
        JsonPath js = convertToJsonObj.obj(readResponse);
        String actualAddress = js.getString("address");
        Assert.assertEquals(actualAddress, newAddress);

    }
    @Test(priority = 2)
    public static void update(){
        String endpoint = "https://rahulshettyacademy.com/maps/api/place/update/json";
        newAddress = "3J, Siaga Raya";
        String updateResponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeId).body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").
                when().put(endpoint).
                then().assertThat().statusCode(200).extract().response().asString();

    }

    @Test(priority = 4)
    public static void delete(){
        String endpoint = "https://rahulshettyacademy.com/maps/api/place/delete/json";
        given().queryParam("key", "qaclick123").body("{\n" +
                "\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}").when().delete(endpoint).then();
    }
}
