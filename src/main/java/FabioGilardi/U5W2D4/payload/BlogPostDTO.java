package FabioGilardi.U5W2D4.payload;

import FabioGilardi.U5W2D4.enums.BlogType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BlogPostDTO(
        @NotNull(message = "blogType is mandatory!")
        BlogType blogType,
        @NotEmpty(message = "title is mandatory!")
        @Size(min = 3, message = "title length must be over 3!")
        String title,
        @NotEmpty(message = "content is mandatory!")
        @Size(min = 3, message = "content size must be over 3")
        String content,
        @NotEmpty(message = "cover is mandatory!")
        String cover,
        @NotNull(message = "reading time is mandatory!")
        @Min(value = 1, message = "reading time must be over 1!")
        int readingTime,
        @Min(value = 1, message = "author id must be over 1!")
        @NotNull(message = "author id is mandatory!")
        long authorId) {
}
