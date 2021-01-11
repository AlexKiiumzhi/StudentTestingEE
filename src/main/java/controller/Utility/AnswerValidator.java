package controller.Utility;

import model.entity.Answer;
import model.entity.Question;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnswerValidator {

    public static  List<String> doValidate(Answer answer) {

        List<String> errors = new ArrayList();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Answer>> cvs = validator.validate(answer);

        if (!cvs.isEmpty()) {

            for (ConstraintViolation<Answer> cv : cvs) {

                String err = cv.getMessage();
                errors.add(err);
            }
        }

        return errors;
    }
}
