package api;

import config.Project;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.VkApiComponents.*;
import static com.codeborne.selenide.Selenide.sleep;
import static helpers.CustomAllureListener.customAllureTemplate;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TinkoffVkApiTests {

    private static final String
            VK_TOKEN = Project.config.vkToken(),
            API_V = "5.131";
    private static final Integer
            VK_ID = Project.config.vkID(),
            GROUP_ID = 20225241;

    @BeforeAll
    static void beforeAll() {
        baseURI = "https://api.vk.com/method/";
        filters(customAllureTemplate());
    }

    @Test
    @Tag("api")
    @Owner("KELONMYOSA")
    @DisplayName("Проверка подписки на группу")
    @Link(value = "VK group URL", url = "https://vk.com/tinkoffbank")
    void subscriptionTest() {
        Integer isMember = isMember(API_V, VK_TOKEN, VK_ID, GROUP_ID);
        if (isMember == 0) {
            groupJoin(API_V, VK_TOKEN, GROUP_ID);
        } else {
            assertThat(isMember).isEqualTo(1);
        }
    }

    @Test
    @Tag("api")
    @Owner("KELONMYOSA")
    @DisplayName("Проверяем, существует ли последняя запись на стене")
    @Link(value = "VK group URL", url = "https://vk.com/tinkoffbank")
    void wallGetTest() {
        String response = wallGet(API_V, VK_TOKEN, GROUP_ID, 1)
                .jsonPath().getString("\"response\".items.size()");
        assertThat(response).isEqualTo("1");
    }

    @Test
    @Tag("api")
    @Owner("KELONMYOSA")
    @DisplayName("Проверяка лайка последней записи")
    @Link(value = "VK group URL", url = "https://vk.com/tinkoffbank")
    void likeTest() {
        Integer lastPostId = Integer.parseInt(wallGet(API_V, VK_TOKEN, GROUP_ID, 1)
                .jsonPath().getString("\"response\".items[0].id"));
        Integer likesCountBefore = Integer.parseInt(wallGet(API_V, VK_TOKEN, GROUP_ID, 1)
                .jsonPath().getString("\"response\".items[0].likes.count"));
        Integer likesCountAfter = Integer.parseInt(addLike(API_V, VK_TOKEN, GROUP_ID, lastPostId)
                .jsonPath().getString("\"response\".likes"));
        assertThat(likesCountAfter).isEqualTo(likesCountBefore + 1);
        sleep(500);
        deleteLike(API_V, VK_TOKEN, GROUP_ID, lastPostId)
                .jsonPath().getString("\"response\".likes");
    }

    @Test
    @Tag("api")
    @Owner("KELONMYOSA")
    @DisplayName("Проверяка комментария последней записи")
    @Link(value = "VK group URL", url = "https://vk.com/tinkoffbank")
    void commentTest() {
        Integer lastPostId = Integer.parseInt(wallGet(API_V, VK_TOKEN, GROUP_ID, 1)
                .jsonPath().getString("\"response\".items[0].id"));
        Integer commentId = Integer.parseInt(createComment(API_V, VK_TOKEN, GROUP_ID, lastPostId, "Cool")
                .jsonPath().getString("\"response\".comment_id"));
        String commentText = getComment(API_V, VK_TOKEN, GROUP_ID, commentId).jsonPath().getString("\"response\".items[0].text");
        assertThat(commentText).isEqualTo("Cool");
        sleep(500);
        deleteComment(API_V, VK_TOKEN, GROUP_ID, commentId);
    }
}
