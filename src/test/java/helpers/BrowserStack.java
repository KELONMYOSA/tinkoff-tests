package helpers;

import static config.Project.config;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class BrowserStack {

    public static String videoUrl(String sessionId) {
        String url = format("https://api-cloud.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(config.browserstackUser(), config.browserstackKey())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
