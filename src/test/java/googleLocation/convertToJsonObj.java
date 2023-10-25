package googleLocation;

import io.restassured.path.json.JsonPath;

public class convertToJsonObj {

    public static String placeId(String response){
        JsonPath js = new JsonPath(response);
        String obj = js.getString("place_id");
        return obj;
    }

    public static String address(String response){
        JsonPath js = new JsonPath(response);
        String obj1 = js.getString("address");
        return obj1;
    }
}
