package googleLocation;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import pojo.*;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
public class serializeLocation {
    static RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").build();
    static ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).build();
    @Test
    public void buildLocationPayload(){

        googleLocationPayload gl = new googleLocationPayload();
        gl.setAccuracy(50);
        gl.setName("Siaga Raya");
        gl.setPhone_number("+91976927");
        gl.setAddress("3J, Side Layout");
        gl.setWebsite("www.google.com");
        gl.setLanguage("English");

        location l = new location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        gl.setLocation(l);

        ArrayList list = new ArrayList<>();
        list.add("Shoe Shop");
        list.add("Park");
        gl.setTypes(list);

        var createResponse = given().spec(req).body(gl).
                when().post("/maps/api/place/add/json").
                then().spec(res).extract().response().asString();
        System.out.println(createResponse);


    }
}
