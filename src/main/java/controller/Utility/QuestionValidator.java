package controller.Utility;

import model.entity.Question;
import model.entity.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QuestionValidator {

    public static  List<String> doValidate(Question question) {

        List<String> errors = new ArrayList();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Question>> cvs = validator.validate(question);

        if (!cvs.isEmpty()) {

            for (ConstraintViolation<Question> cv : cvs) {

                String err = cv.getMessage();
                errors.add(err);
            }
        }

        return errors;
    }
}
