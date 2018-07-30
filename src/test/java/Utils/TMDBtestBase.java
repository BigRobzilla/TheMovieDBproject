package Utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class TMDBtestBase {
    //URLs
    protected final static String TMDB_URL = "www.themoviedb.org";
    //Language Options
    protected final static String ENGLISH = "en";
    protected final static String FRENCH = "fr";
    protected final static String SPANISH = "es";
    protected final static String JAPANESE = "ja";
    protected final static String INVALID_LANGUAGE = "xx";
    //TODO - add languages as application support extends

    protected static String API_KEY;
    protected static String SESSION_ID;


    @BeforeClass
    public static void setup() {
        API_KEY = System.getenv("API_KEY");
    }

    @AfterClass
    public static void cleanUp() {

    }

    // ---------- Utility Methods ----------
    protected String getGETresponse(int statusCode, String url, String... params) {
        return getGETresponse(API_KEY, statusCode, url, params);
    }

    protected String getGETresponse(String apiKey, int statusCode, String url, String... params) {
        RequestSpecification request = RestAssured.given();
        for (String param: params) {
            String[] pieces = param.split("=");
            request.queryParam(pieces[0], pieces[1]);
        }
        return request
                .queryParam("api_key", apiKey)
                .get(url)
                .then().statusCode(statusCode).extract().body().toString();
    }

    protected String getPOSTresponse(int statusCode, String url, String body, String... params) {
        return getPOSTresponse(API_KEY, statusCode, url, body, params);
    }

    protected String getPOSTresponse(String apiKey, int statusCode, String url, String body, String... params) {
        RequestSpecification request = RestAssured.given();
        for (String param: params) {
            String[] pieces = param.split("=");
            request.queryParam(pieces[0], pieces[1]);
        }
        return request
                .queryParam("api_key", apiKey)
                .body(body)
                .post(url)
                .then().statusCode(statusCode).extract().body().toString();
    }
}
