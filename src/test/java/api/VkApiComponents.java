package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class VkApiComponents {

    static Integer isMember(String apiV, String vkToken, Integer vkId, Integer groupId) {
        return given()
                .contentType(ContentType.JSON)
                .param("v", apiV)
                .param("access_token", vkToken)
                .param("user_id", vkId)
                .param("group_id", groupId)
                .when()
                .get("groups.isMember")
                .then()
                .statusCode(200)
                .extract().response().path("\"response\"");
    }

    static void groupJoin(String apiV, String vkToken, Integer groupId) {
        given()
                .contentType(ContentType.JSON)
                .param("v", apiV)
                .param("access_token", vkToken)
                .param("group_id", groupId)
                .when()
                .get("groups.join")
                .then()
                .statusCode(200)
                .body("\"response\"", is(1));
    }

    static Response wallGet(String apiV, String vkToken, Integer groupId, Integer count) {
        return given()
                .contentType(ContentType.JSON)
                .param("v", apiV)
                .param("access_token", vkToken)
                .param("owner_id", -groupId)
                .param("count", count)
                .when()
                .get("wall.get")
                .then()
                .statusCode(200)
                .extract().response();
    }

    static Response addLike(String apiV, String vkToken, Integer groupId, Integer postId) {
        return given()
                .contentType(ContentType.JSON)
                .param("v", apiV)
                .param("access_token", vkToken)
                .param("owner_id", -groupId)
                .param("post_id", postId)
                .when()
                .get("wall.addLike")
                .then()
                .statusCode(200)
                .extract().response();
    }

    static Response deleteLike(String apiV, String vkToken, Integer groupId, Integer postId) {
        return given()
                .contentType(ContentType.JSON)
                .param("v", apiV)
                .param("access_token", vkToken)
                .param("owner_id", -groupId)
                .param("post_id", postId)
                .when()
                .get("wall.deleteLike")
                .then()
                .statusCode(200)
                .extract().response();
    }

    static Response createComment(String apiV, String vkToken, Integer groupId, Integer postId, String message) {
        return given()
                .contentType(ContentType.JSON)
                .param("v", apiV)
                .param("access_token", vkToken)
                .param("owner_id", -groupId)
                .param("post_id", postId)
                .param("message", message)
                .when()
                .get("wall.createComment")
                .then()
                .statusCode(200)
                .extract().response();
    }

    static Response getComment(String apiV, String vkToken, Integer groupId, Integer commentId) {
        return given()
                .contentType(ContentType.JSON)
                .param("v", apiV)
                .param("access_token", vkToken)
                .param("owner_id", -groupId)
                .param("comment_id", commentId)
                .when()
                .get("wall.getComment")
                .then()
                .statusCode(200)
                .extract().response();
    }

    static void deleteComment(String apiV, String vkToken, Integer groupId, Integer commentId) {
        given()
                .contentType(ContentType.JSON)
                .param("v", apiV)
                .param("access_token", vkToken)
                .param("owner_id", -groupId)
                .param("comment_id", commentId)
                .when()
                .get("wall.deleteComment")
                .then()
                .statusCode(200)
                .body("\"response\"", is(1));
    }
}