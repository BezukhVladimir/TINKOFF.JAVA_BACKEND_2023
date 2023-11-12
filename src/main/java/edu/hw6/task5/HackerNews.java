package edu.hw6.task5;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HackerNews {
    private static final URI BASE_URL = URI.create("https://hacker-news.firebaseio.com/v0/");
    private static final URI TOP_STORIES_URL = BASE_URL.resolve("topstories.json");
    private static final URI ITEM_URL = BASE_URL.resolve("item/");
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10L);

    private final HttpClient client;
    private final Gson gson;

    public HackerNews(HttpClient client) {
        this.client = client;
        this.gson = new Gson();
    }

    public long[] hackerNewsTopStories() {
        HttpResponse<String> response = getResponse(
            HttpRequest.newBuilder()
                .uri(TOP_STORIES_URL)
                .timeout(DEFAULT_TIMEOUT)
                .GET()
                .build()
        );

        return parseTopStories(response);
    }

    public String news(long id) {
        HttpResponse<String> response = getResponse(
            HttpRequest.newBuilder()
                .uri(ITEM_URL.resolve(id + ".json"))
                .timeout(DEFAULT_TIMEOUT)
                .GET()
                .build()
        );

        HackerNewsStory news = parseStory(response);

        return news.title();
    }

    private long[] parseTopStories(HttpResponse<String> response) {
        String responseBody = response.body();
        return gson.fromJson(responseBody, long[].class);
    }

    private HackerNewsStory parseStory(HttpResponse<String> response) {
        String responseBody = response.body();
        return gson.fromJson(responseBody, HackerNewsStory.class);
    }

    private HttpResponse<String> getResponse(HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
