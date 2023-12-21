package edu.hw06.task5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.net.http.HttpClient;
import static org.assertj.core.api.Assertions.assertThat;

class HackerNewsTest {
    private final HttpClient client = HttpClient.newHttpClient();

    @Test
    @Disabled
    void testHackerNewsTopStories() {
        // Arrange
        // https://github.com/HackerNews/API#new-top-and-best-stories
        int top500 = 500;
        HackerNews hackerNews = new HackerNews(client);

        // Act
        long[] ids = hackerNews.hackerNewsTopStories();

        // Assert
        assertThat(ids).hasSize(top500);
    }

    @Test
    void testNews() {
        // Arrange
        // https://github.com/HackerNews/API#items
        int exampleStoryId = 8863;
        String expected = "My YC app: Dropbox - Throw away your USB drive";
        HackerNews hackerNews = new HackerNews(client);

        // Act
        String result = hackerNews.news(exampleStoryId);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
