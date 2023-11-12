package edu.hw6.task5;

import java.util.List;

public record HackerNewsStory(
    String by,
    int descendants,
    long id,
    List<Long> kids,
    int score,
    long time,
    String title,
    String type,
    String url
) {
}
