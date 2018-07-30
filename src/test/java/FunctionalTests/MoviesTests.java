package FunctionalTests;


import Utils.TMDBtestBase;
import com.google.gson.JsonObject;
import org.junit.Test;

public class MoviesTests extends TMDBtestBase {

    private static final String MOVIES_BASE_URL = TMDB_URL + "/3/movie/";
    private static final String ACCOUNT_STATES = "/account_states";
    private static final String ALTERNATIVE_TITLES = "/alternative_titles";
    private static final String CHANGES = "/changes";
    private static final String CREDITS = "/credits";
    private static final String EXTERNAL_IDS = "/external_ids";
    private static final String IMAGES = "/images";
    private static final String KEYWORDS = "/keywords";
    private static final String RELEASE_DATES = "/release_dates";
    private static final String VIDEOS = "/videos";
    private static final String TRANSLATIONS = "/translations";
    private static final String RECOMMENDATIONS = "/recommendations";
    private static final String SIMILAR_MOVIES = "/similar";
    private static final String REVIEWS = "/reviews";
    private static final String LISTS = "/lists";
    private static final String RATING = "/rating";
    private static final String LATEST = "/latest";
    private static final String NOW_PLAYING = "/now_playing";
    private static final String POPULAR = "/popular";
    private static final String TOP_RATED = "/top_rated";
    private static final String UPCOMING = "/upcoming";

    private static final String TEST_MOVIE_ID = "24020-johnny-tsunami";

    // ---------- GET Details Tests ----------
    @Test
    public void getDetails_SimpleSuccess() {
        String response = getGETresponse(200, MOVIES_BASE_URL + TEST_MOVIE_ID);
        // Verify that all the information for the test movie is returned
        // And that all default parameters were used
    }

    @Test
    public void getDetails_LanguageEnglish() {
        String response = getGETresponse(200, MOVIES_BASE_URL + TEST_MOVIE_ID,
                "language=" + ENGLISH);
        // Verify that all the correct information was returned in English
    }

    @Test
    public void getDetails_LanguageFrench() {
        String response = getGETresponse(200, MOVIES_BASE_URL + TEST_MOVIE_ID,
                "language=" + FRENCH);
        // Verfy that all the correct information was returned in French
    }

    @Test
    public void getDetails_LanguageSpanish() {
        String response = getGETresponse(200, MOVIES_BASE_URL + TEST_MOVIE_ID,
                "language=" + SPANISH);
        // Verfy that all the correct information was returned in Spanish
    }

    @Test
    public void getDetails_LanguageJapanese() {
        String response = getGETresponse(200, MOVIES_BASE_URL + TEST_MOVIE_ID,
                "language=" + JAPANESE);
        // Verfy that all the correct information was returned in Japanese
    }

    @Test
    public void getDetails_LanguageInvalid() {
        String response = getGETresponse(500, MOVIES_BASE_URL + TEST_MOVIE_ID,
                "language=" + INVALID_LANGUAGE);
        // Verify that the response fails when using an invalid language
    }

    @Test
    public void getDetails_AppendToResponseAccountStates() {
        String response = getGETresponse(200, MOVIES_BASE_URL + TEST_MOVIE_ID,
                "append_to_response=" + ACCOUNT_STATES);
        // Verify that both the Details and Account States information is returned

        //              |
        // AppendToResponse Alternative Titles
        //              |
        // AppendToResponse Changes
        //              |
        // AppendToResponse Credits
        //              |
        // AppendToResponse External IDs
        //              |
        // AppendToResponse Images
        //              |
        // AppendToResponse Keywords
        //              |
        // AppendToResponse Release Dates
        //              |
        // AppendToResponse Videos
        //              |
        // AppendToResponse Translations
        //              |
        // AppendToResponse Recommendations
        //              |
        // AppendToResponse Similar
        //              |
        // AppendToResponse Reviews
        //              |
        // AppendToResponse Lists
        //              |
        // AppendToResponse Invalid
    }

    @Test
    public void getDetails_LanguageAndAppendToResponse() {
        String response = getGETresponse(200, MOVIES_BASE_URL + TEST_MOVIE_ID,
                "language=" + FRENCH, "append_to_response=" + ACCOUNT_STATES);
        // Verify that both parameters are correctly enforced
    }

    @Test
    public void getDetails_InvalidApiKey() {
        String response = getGETresponse("invalid_api_key", 401, MOVIES_BASE_URL + TEST_MOVIE_ID);
        // Verify response fails
    }

    @Test
    public void getDetails_NotFound() {
        String response = getGETresponse(404, MOVIES_BASE_URL + "Non-Existent_Movie");
        // Verify the error message
    }

    // ---------- GET Account States Tests ----------
    //                      |
    // ---------- GET Alternative Titles Tests ----------
    //                      |
    // ---------- GET Changes Tests ----------
    //                      |
    // ---------- GET Credits Tests ----------
    //                      |
    // ---------- GET External IDs Tests ----------
    //                      |
    // ---------- GET Images Tests ----------
    //                      |
    // ---------- GET Keywords Tests ----------
    //                      |
    // ---------- GET Release Dates Tests ----------
    //                      |
    // ---------- GET Videos Tests ----------
    //                      |
    // ---------- GET Translations Tests ----------
    //                      |
    // ---------- GET Recommendations Tests ----------
    //                      |
    // ---------- GET Similar Tests ----------
    //                      |
    // ---------- GET Reviews Tests ----------
    //                      |
    // ---------- GET Lists Tests ----------

    // ---------- POST Rate Movie Tests ----------
    @Test
    public void postRateMovieSimpleSuccess() {
        JsonObject body = new JsonObject();
        body.addProperty("value", 5);

        String response = getPOSTresponse(200, MOVIES_BASE_URL + TEST_MOVIE_ID + RATING, body.toString());
        //Verify the correct value is saved on the rating.
        //Likely using a GET call to verify the rating is returned
    }

    @Test
    public void postRateMovieBelowMinimum() {
        JsonObject body = new JsonObject();
        body.addProperty("value", 0);

        String response = getPOSTresponse(500, MOVIES_BASE_URL + TEST_MOVIE_ID + RATING, body.toString());
        //Verify the test fails with the correct message
    }

    @Test
    public void postRateMovieAboveMaximum() {
        JsonObject body = new JsonObject();
        body.addProperty("value", 10.5);

        String response = getPOSTresponse(500, MOVIES_BASE_URL + TEST_MOVIE_ID + RATING, body.toString());
        //Verify the test fails with the correct message
    }

    @Test
    public void postRateMovieInvalidBody() {
        String response = getPOSTresponse(400, MOVIES_BASE_URL + TEST_MOVIE_ID + RATING, "value=5");
        //Verify the test fails with the correct message
    }

    @Test
    public void postRateMovieNonExistentMovieID() {
        JsonObject body = new JsonObject();
        body.addProperty("value", 10.5);

        String response = getPOSTresponse(404, MOVIES_BASE_URL + "Non-Existent_Movie" + RATING, body.toString());
        //Verify the test fails with the correct message
    }

    @Test
    public void postRateMovieInvalidApiKey() {
        JsonObject body = new JsonObject();
        body.addProperty("value", 5);

        String response = getPOSTresponse("invalid_api_key", 500, MOVIES_BASE_URL + TEST_MOVIE_ID + RATING, body.toString());
        //Verify the test fails with the correct message
    }

    // ---------- DELETE Delete Rating Tests ----------

    // ---------- GET Latest Tests ----------
    //                      |
    // ---------- GET Now Playing Tests ----------
    //                      |
    // ---------- GET Popular Tests ----------
    //                      |
    // ---------- GET Top Rated Tests ----------
    //                      |
    // ---------- GET Upcoming Tests ----------
}
