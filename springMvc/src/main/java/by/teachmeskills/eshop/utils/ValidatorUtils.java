package by.teachmeskills.eshop.utils;

import by.teachmeskills.eshop.enums.State;
import by.teachmeskills.eshop.exceptions.RequestParamNullException;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import static java.util.Arrays.stream;

@UtilityClass
public class ValidatorUtils {
    private static final String REQUEST_PARAMETER_IS_NULL_ERROR = "Request parameter is not initialized!";

    public static String userDataValidation(HashMap<String, String> userData, LocalDate birth) throws RequestParamNullException {
        String errorMessage = "";
        if (stream(userData.values().toArray()).anyMatch(Objects::isNull)) {
            throw new RequestParamNullException(REQUEST_PARAMETER_IS_NULL_ERROR);
        } else {
            if (!userData.get("name").matches("^[а-яА-Яa-zA-Z]+$")) {
                errorMessage += State.INVALID_NAME.getState();
            }
            if (!userData.get("surname").matches("^[а-яА-Яa-zA-Z]+$")) {
                errorMessage += State.INVALID_LASTNAME.getState();
            }
            Period period = Period.between(birth, LocalDate.now());
            if (!(period.getYears() >= 16)) {
                errorMessage += State.INVALID_BIRTHDATE.getState();

            }
            if (!userData.get("email").matches("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
                errorMessage += State.INVALID_EMAIL.getState();
            }
            if (!userData.get("password").equals(userData.get("repPassword")) || (userData.get("password").length() > 10 || userData.get("password").length() < 6)) {
                errorMessage += State.INVALID_PASSWORD.getState();
            }
            if (errorMessage.length() > 0) {
                return errorMessage;
            }
            return State.VALID.getState();
        }

    }

    public static void validateParamNotNull(String... parameters) throws RequestParamNullException {
        if (stream(parameters).anyMatch(Objects::isNull)) {
            throw new RequestParamNullException(REQUEST_PARAMETER_IS_NULL_ERROR);
        }
    }

}
