package com.uniovi.sdi2324808spring.validators;

import com.uniovi.sdi2324808spring.entities.Mark;
import com.uniovi.sdi2324808spring.entities.User;
import com.uniovi.sdi2324808spring.services.MarksService;
import com.uniovi.sdi2324808spring.services.UsersService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class AddMarkValidator implements Validator {
    private final MarksService marksService;

    public AddMarkValidator(MarksService marksService) {

        this.marksService = marksService;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
        if (mark.getScore()<0 || mark.getScore()>10) {
            errors.rejectValue("score", "Mark.score.valueError");}
        if (mark.getDescription().length()<20) {
            errors.rejectValue("description", "Mark.description.shortError");}

    }
}
