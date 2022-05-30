package lab1.rest.contracts;

import lab1.jpa.entities.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LecturerDTO {

    private Integer id;

    private String firstName;
    private String lastName;
    private String name;

    private List<Course> courses;

    private UniversityDTO university;

}
