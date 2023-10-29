package stepDefinitions;
 import io.cucumber.java.en.Given;
 import io.cucumber.java.en.Then;
 import io.cucumber.java.en.When;
 import io.restassured.builder.RequestSpecBuilder;
 import io.restassured.builder.ResponseSpecBuilder;
 import io.restassured.path.json.JsonPath;
 import io.restassured.response.Response;
 import io.restassured.specification.RequestSpecification;
 import io.restassured.specification.ResponseSpecification;
 import org.testng.Assert;
 import org.testng.annotations.Test;
 import pojo.googleLocationPayload;
 import pojo.location;
 import static org.junit.Assert.*;
 import googleLocation.*;

 import java.util.ArrayList;

 import static io.restassured.RestAssured.given;

public class stepDefintion {
    static RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").build();
    static ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).build();
    googleLocationPayload gl = new googleLocationPayload();;
    location l = new location();;
            Response createResponse;
    @Given("User has Add Location payload")
    public void user_has_add_location_payload() {


            gl.setAccuracy(50);
            gl.setName("Siaga Raya");
            gl.setPhone_number("+91976927");
            gl.setAddress("3J, Side Layout");
            gl.setWebsite("www.google.com");
            gl.setLanguage("English");

            l.setLat(-38.383494);
            l.setLng(33.427362);
            gl.setLocation(l);

            ArrayList list = new ArrayList<>();
            list.add("Shoe Shop");
            list.add("Park");
            gl.setTypes(list);

        }

    @When("User calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {

        createResponse = given().spec(req).body(gl).
                when().post("/maps/api/place/add/json").
                then().spec(res).extract().response();
        System.out.println(createResponse);
    }
    @Then("User should see success status code {int}")
    public void user_should_see_success_status_code(Integer int1) {
        int actualStatusCode = createResponse.getStatusCode();
        assertEquals(actualStatusCode, 200);
    }
    @Then("{string} is {string}")
    public void is(String string, String string2) {
        String createResp = createResponse.asString();
        JsonPath js = convertToJsonObj.obj(createResp);
        String actualStatus = js.get(string);
        assertEquals(actualStatus, string2);
    }
}
