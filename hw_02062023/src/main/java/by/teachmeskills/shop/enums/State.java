package by.teachmeskills.shop.enums;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public enum State {
    VALID("Успешно"),
    INVALID_NAME("Неверное имя "),
    INVALID_LASTNAME("Неверная фамилия "),
    INVALID_BIRTHDATE("Неверная дата рождения "),
    INVALID_EMAIL("Неверный email "),
    INVALID_PASSWORD("Неверный пароль ");

    private String state;

    State(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
