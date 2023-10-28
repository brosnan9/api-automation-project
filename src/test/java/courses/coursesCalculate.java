package courses;

import googleLocation.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class coursesCalculate {

    @Test (priority = 4)
    public static void iterateOverCourses(){

        //Parsing response of courses Json
        JsonPath js = convertToJsonObj.obj(payload.courses());

        //Print number of courses
        int coursesCount = js.getInt("courses.size()");
        System.out.println(coursesCount);

        //Print purchase amount of all courses
        int expectedPurchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(expectedPurchaseAmount);

        //Print title of all courses
        for(int i = 0 ; i<coursesCount ; i++) {
            String courseTitle = js.getString("courses["+i+"].title");
            int coursePrice = js.getInt("courses["+i+"].price");
            System.out.println("The course title is "+courseTitle);
            System.out.println("The price of "+courseTitle+" is "+coursePrice);
        }

        //Verify if Sum of all Course prices matches with Purchase Amount
        int actualPurchaseAmount = 0;
        for(int i = 0 ; i<coursesCount ; i++) {
            int coursePrice = js.getInt("courses["+i+"].price");
            int courseCopies = js.getInt("courses["+i+"].copies");
            actualPurchaseAmount = actualPurchaseAmount + (coursePrice*courseCopies);
        }
        System.out.println("The actual purchase amount is "+actualPurchaseAmount);

        Assert.assertEquals(actualPurchaseAmount, expectedPurchaseAmount);
    }

}
