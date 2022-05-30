package lab1.usecases;

import lab1.jpa.entities.Course;
import lab1.jpa.entities.Lecturer;
import lab1.jpa.entities.University;
import lab1.jpa.persistence.CoursesDAO;
import lab1.jpa.persistence.UniversitiesDAO;
import lab1.services.CourseGenerator;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    @Inject
    private CourseGenerator courseGenerator;

    private CompletableFuture<String> loginNameGenerationTask = null;

    @Getter
    @Setter
    private long currentCourseId;

    @PostConstruct
    public void init() {
        loadAllCourses();
        newCourse.setUniversity(new University());
        newCourse.setLecturer(new Lecturer());
    }

    public Course findById(int id) {
        return courses.findById(id);
    }

    public List<Course> findByLecturerId(int lecturerId) {
        return courses.findByLecturerId(lecturerId);
    }

    public int getCourses(int lecturerId) {
        return findByLecturerId(lecturerId).size();
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
