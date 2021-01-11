package controller.Utility;

import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.regex.Pattern;

public class ParameterValidator {

    public boolean validateLogin(String email, String password) {
        if (email == null || email.equals("") || password == null || password.equals("") ||
                Pattern.compile(".{1,50}@[a-zA-z]{1,15}\\.[a-zA-Z]{1,5}").matcher(email).matches()) {
            return false;
        }
        return true;
    }

    public boolean validateEmpty(String string) {
        if (string.equals("")) {
            return false;
        }
        return true;
    }

    public boolean validateEmptyList(List<Long> list) {
        if (list.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validateEmptyArray(String[] array) {
        if (ArrayUtils.isEmpty(array)) {
            return false;
        }
        return true;
    }

    public boolean validateNullNumber(String number) {
        if (number == null || number.equals("")) {
            return false;
        }
        return true;
    }

    public boolean validateNullTwoNumbers(String number1, String number2) {
        if (number1 == null || number1.equals("")) {
            return false;
        }else if (number2 == null || number2.equals("")){
            return  false;
        }
        return true;
    }

    public boolean validateNullThreeNumbers(String number1, String number2, String number3) {
        if (number1 == null || number1.equals("")) {
            return false;
        }else if (number2 == null || number2.equals("")){
            return  false;
        }else if (number3 == null || number3.equals("")){
            return  false;
        }
        return true;
    }
}

