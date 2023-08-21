package by.teachmeskills.eshop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@SuperBuilder
public class User extends BaseEntity {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private String password;

    public User() {

    }
}
