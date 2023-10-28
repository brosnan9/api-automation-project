package library;
import static io.restassured.RestAssured.*;
import googleLocation.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dynamicLibrary {

    public static String bookId;

    @Test(dataProvider = "BooksData")
    public static void addBook(String isbn, String aisle) {
        String addEndpoint = "https://rahulshettyacademy.com/Library/Addbook.php";
        String addBookResponse = given().body(payload.library(isbn, aisle)).
                when().post(addEndpoint).
                then().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = convertToJsonObj.obj(addBookResponse);
        bookId = js.getString("ID");
    }

    @Test
    public static void deleteBook(){
        String deleteEndpoint = "https://rahulshettyacademy.com/Library/DeleteBook.php";
        String deleteBookResponse = given().body(payload.deleteLibrary(bookId)).
                when().delete(deleteEndpoint).
                then().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = convertToJsonObj.obj(deleteBookResponse);
        String deleteConfirmationMessage = js.getString("msg");
        Assert.assertEquals(deleteConfirmationMessage, "book is successfully deleted");
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData(){
        return new Object[][] {{"ger","456"},{"svet","934"},{"jurr", "343"} };
    }

}
