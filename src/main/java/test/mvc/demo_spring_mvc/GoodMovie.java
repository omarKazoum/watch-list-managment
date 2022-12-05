package test.mvc.demo_spring_mvc;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy=GoodMovieValidator.class)

public @interface GoodMovie {
    String message() default "if a movie is as good as 8 then priority should be at leat M!";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
