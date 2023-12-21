package edu.hw10.task1.models;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public record RecordEmployee (
    @Min(18) @Max(70) int age,
    @NotNull String name
) {
}
