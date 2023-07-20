package by.teachmeskills.shop.utils;

import java.time.LocalDate;
import java.time.Period;

public class ValidatorUtils {
    private ValidatorUtils() {

    }

    public static State nameValidation(String name) {
        if (!name.matches("^[а-яА-Яa-zA-Z]+$")) {
            return State.INVALID_NAME;
        }
        return State.VALID;
    }

    public static State surnameValidation(String lastname) {
        if (!lastname.matches("^[а-яА-Яa-zA-Z]+$")) {
            return State.INVALID_LASTNAME;
        }
        return State.VALID;
    }

    public static State birthdateValidation(LocalDate date) {
        Period period = Period.between(date, LocalDate.now());
        if (!(period.getYears() >= 16)) {
            return State.INVALID_BIRTHDATE;

        }
        return State.VALID;
    }

    public static State emailValidation(String email) {
        if (!email.matches("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
            return State.INVALID_EMAIL;
        }
        return State.VALID;
    }

    public static State passwordValidation(String pass, String repPass) {
        if (!pass.equals(repPass) || (pass.length() > 10 || pass.length() < 6)) {
            return State.INVALID_PASSWORD;
        }
        return State.VALID;
    }

    public static String userDataValidation(String name, String surname, LocalDate birth, String email, String password, String repPassword) {
        String errorMessage = "";
        if (nameValidation(name) != State.VALID) {
            errorMessage += State.INVALID_NAME.getState();
        }
        if (surnameValidation(surname) != State.VALID) {
            errorMessage += State.INVALID_LASTNAME.getState();
        }
        if (birthdateValidation(birth) != State.VALID) {
            errorMessage += State.INVALID_BIRTHDATE.getState();
        }
        if (emailValidation(email) != State.VALID) {
            errorMessage += State.INVALID_EMAIL.getState();
        }
        if (passwordValidation(password, repPassword) != State.VALID) {
            errorMessage += State.INVALID_PASSWORD.getState();
        }
        if (errorMessage.length() > 0) {
            return errorMessage;
        }
        return State.VALID.getState();

    }

}
