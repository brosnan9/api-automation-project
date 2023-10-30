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
 import resources.APIresources;
 import resources.TestDataBuild;
 import resources.utils;

 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.util.ArrayList;

 import static io.restassured.RestAssured.given;

public class stepDefintion extends utils {
    //static ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).build();
    TestDataBuild db = new TestDataBuild();
    RequestSpecification req;
    Response createResponse;

    @Given("User has Add Location payload with {string} {string} {string}")
    public void userHasAddLocationPayloadWith(String name, String language, String address) throws IOException {

        req = given().spec(reqSpecBuilder()).body(db.addPlacePayload(name, language, address));
        }
    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {

        //Creating object of enum class to fetch value of endpoint, constructor is called of enum class
    APIresources apiValue = APIresources.valueOf(resource);
    apiValue.getResource();

        if(method.equalsIgnoreCase("POST")) {
        createResponse = req.
                when().post(apiValue.getResource());

        } else if (method.equalsIgnoreCase("GET")) {
            createResponse = req.
                    when().get(apiValue.getResource());
        }
    }
    @Then("User should see success status code {int}")
    public void user_should_see_success_status_code(Integer code) {
        int actualStatusCode = createResponse.getStatusCode();
        assertEquals(actualStatusCode, 200);
    }
    @Then("{string} is {string}")
    public void is(String keyValue, String expectedValue) {

        assertEquals(getJsonPath(createResponse, keyValue), expectedValue);
    }
    @Then("verify that place_id has value same as {string} using {string}")
    public void verify_that_is_has_value_same_as_using(String name, String resource) throws IOException {

        String actualPlaceid = getJsonPath(createResponse, "place_id");
        req = given().spec(reqSpecBuilder()).queryParam("place_id", actualPlaceid);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(createResponse, "name");
        assertEquals(actualName, name);
    }

}
