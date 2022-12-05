package test.mvc.demo_spring_mvc;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class PriorityValidator implements ConstraintValidator<Priority,String> {


    @Override
    public boolean isValid(String p, ConstraintValidatorContext constraintValidatorContext) {
       return p.equals("M") ||p.equals("H") || p.equals("L");
    }
}
