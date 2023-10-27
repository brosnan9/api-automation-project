package googleLocation;

import io.restassured.path.json.JsonPath;

public class convertToJsonObj {

    public static JsonPath obj(String response){
        JsonPath js = new JsonPath(response);
        return js;
    }
}
