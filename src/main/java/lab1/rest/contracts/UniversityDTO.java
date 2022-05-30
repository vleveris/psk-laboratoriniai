package lab1.rest.contracts;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UniversityDTO {
    private int id;
    private String name;

    private List<CourseDTO> courses;

    private LecturerDTO lecturersWithCourses;
}
