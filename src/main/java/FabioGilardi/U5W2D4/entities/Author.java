package FabioGilardi.U5W2D4.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String name, surname, email;
    private LocalDate birthday;

    @Setter(AccessLevel.NONE)
    private String avatar;

    public Author(String name, String surname, String email, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
        setAvatar();
    }

    public void setAvatar() {
        this.avatar = "https://ui-avatars.com/api/?name=" + this.name + "+" + this.surname;
    }
}
