package com.uniovi.sdi2324808spring.validators;

import com.uniovi.sdi2324808spring.entities.Professor;
import com.uniovi.sdi2324808spring.entities.User;
import com.uniovi.sdi2324808spring.services.ProfessorsService;
import com.uniovi.sdi2324808spring.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddProfessorValidator implements Validator {
    private final ProfessorsService professorsService;
    @Autowired
    private  UsersService usersService;
    public AddProfessorValidator(ProfessorsService professorsService) {
        this.professorsService = professorsService;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Professor professor = (Professor) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "DNI", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "Error.empty");
        if(!professor.getDNI().isEmpty()){
            if (professor.getDNI().length() != 9) {
                errors.rejectValue("DNI", "Professor.dni.length");}
            else{
                if (!Character.isAlphabetic(professor.getDNI().charAt(8))) {
                    errors.rejectValue("DNI", "Professor.dni.lastchar");}
            }

        }
        if(professorsService.findProfessorByDNI(professor.getDNI())!=null){
            errors.rejectValue("DNI", "Professor.dni.repeated");
        }
        if(usersService.getUserByDni(professor.getDNI())!=null){
            errors.rejectValue("DNI", "Professor.dni.repeated");
        }

    }
}
