package lab1.usecases;

import lab1.mybatis.model.*;
import lab1.mybatis.dao.CourseMapper;
import lab1.mybatis.dao.UniversityMapper;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CoursesMyBatis {
    @Inject
    private CourseMapper courseMapper;
    @Inject
    private UniversityMapper universityMapper;

    @Getter
    @Setter
    private Course newCourse = new Course();

    @Getter
    private List<Course> allCourses;


    @PostConstruct
    public void init() {
        loadAllCourses();

    }

    public Course findById(int id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void add() {
        courseMapper.insert(newCourse);
    }

    private void loadAllCourses() {
        allCourses = courseMapper.selectAll();
    }
}
