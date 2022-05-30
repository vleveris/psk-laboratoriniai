package lab1.rest.contracts;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CourseDTO {
    private long id;

    private String title;

    private LecturerDTO lecturer;

    private UniversityDTO university;

    private long numberOfStudents;

}
