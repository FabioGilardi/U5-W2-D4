package FabioGilardi.U5W2D4.entities;

import FabioGilardi.U5W2D4.enums.BlogType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Enumerated(EnumType.STRING)
    private BlogType blogType;

    private String title, content, cover;
    private int readingTime;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public BlogPost(BlogType blogType, String title, String content, String cover, int readingTime, Author author) {
        this.blogType = blogType;
        this.title = title;
        this.content = content;
        this.cover = cover;
        this.readingTime = readingTime;
        this.author = author;
    }
}
