package controller.Utility;

import model.entity.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserValidator {

    public static  List<String> doValidate(User user) {

        List<String> errors = new ArrayList();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> cvs = validator.validate(user);

        if (!cvs.isEmpty()) {

            for (ConstraintViolation<User> cv : cvs) {

                String err = cv.getMessage();
                errors.add(err);
            }
        }

        return errors;
    }
}
