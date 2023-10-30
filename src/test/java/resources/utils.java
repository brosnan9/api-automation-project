package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import googleLocation.*;

import java.io.*;
import java.util.Properties;

public class utils {

    public static RequestSpecification req;
    public RequestSpecification reqSpecBuilder() throws IOException {
        if (req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(globalValues("baseUrl")).addQueryParam("key", "qaclick123").
                    addFilter(RequestLoggingFilter.logRequestTo(log)).
                    addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return req;
        }
        return req;
    }
    public static String globalValues(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream f = new FileInputStream("/Users/gersonbrosnanpereira/gersonAPI/firstAPI/src/test/java/resources/global.properties");
        prop.load(f);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response createResponse, String key){

        String createResp = createResponse.asString();
        JsonPath js = convertToJsonObj.obj(createResp);
        return js.get(key).toString();

    }


}
