package controller.Utility;

import model.entity.Test;
import model.entity.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestValidator {

    public static  List<String> doValidate(Test test) {

        List<String> errors = new ArrayList();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Test>> cvs = validator.validate(test);

        if (!cvs.isEmpty()) {

            for (ConstraintViolation<Test> cv : cvs) {

                String err = cv.getMessage();
                errors.add(err);
            }
        }

        return errors;
    }
}
