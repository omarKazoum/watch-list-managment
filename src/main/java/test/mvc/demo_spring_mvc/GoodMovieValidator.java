package test.mvc.demo_spring_mvc;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import test.mvc.demo_spring_mvc.entities.WatchlistItem;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie,WatchlistItem> {
    @Override
    public boolean isValid(WatchlistItem watchlistItem, ConstraintValidatorContext constraintValidatorContext) {
        return watchlistItem.getRating()!=null && !watchlistItem.getRating().isEmpty() && !(Double.valueOf(watchlistItem.getRating()) >=8 && watchlistItem.getPriority().equals("L")) ;
    }
}
