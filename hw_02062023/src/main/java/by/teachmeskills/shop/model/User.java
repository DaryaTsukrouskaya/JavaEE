package by.teachmeskills.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class User {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private String password;

    public User() {
        this.name = "isEmpty";
        this.password = "isEmpty";
    }
}
