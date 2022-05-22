package lab1.usecases;

import lab1.jpa.entities.*;
import lab1.jpa.persistence.CoursesDAO;
import lab1.jpa.persistence.UniversitiesDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CoursesJPA {
    @Inject
    private CoursesDAO courses;

    @Inject
    private UniversitiesDAO universities;

    @Getter
    @Setter
    private Course newCourse = new Course();

    @Getter
    private List<Course> allCourses;

    @Getter
    private List<University> allUniversities;

    @PostConstruct
    public void init() {
        loadAllCourses();
        newCourse.setUniversity(new University());
        newCourse.setLecturer(new Lecturer());
    }

    public Course findById(int id) {
        return courses.findById(id);
    }

    @Transactional
    public void add() {
        int id = newCourse.getUniversity().getId();
        University university = universities.findById(id);
        university.getCoursesWithLecturers().add(newCourse.getLecturer());
        courses.persist(newCourse);
        universities.update(university);
    }

    private void loadAllCourses() {
        allCourses = courses.loadAll();
        allUniversities = universities.loadAll();
    }
}
