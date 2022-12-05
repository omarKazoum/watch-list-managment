package test.mvc.demo_spring_mvc.entities;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import test.mvc.demo_spring_mvc.GoodMovie;
import test.mvc.demo_spring_mvc.Priority;


@Data
@AllArgsConstructor
@GoodMovie
public class WatchlistItem {
    private Integer id;
    @NotEmpty(message = "please enter the title")
    private String title;
    @Min(value = 5,message = "rating should be at least 5 stars!")
    @Max(value = 10,message = "rating should be at most five stars!")
    private String rating;
    @Priority
    private String priority;
    @Size(max = 50,message = "comment should contain less than 50 characters!")
    private String comment;

    public static int index = 0;

    public WatchlistItem() {
        this.id = ++index ;
    }

}
