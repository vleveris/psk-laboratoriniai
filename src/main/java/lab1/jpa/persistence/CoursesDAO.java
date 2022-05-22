package lab1.jpa.persistence;

import lab1.jpa.entities.Course;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CoursesDAO {
    @Inject
    private EntityManager em;

    @Transactional
    public List<Course> loadAll() {
        return em.createNamedQuery("Course.findAll", Course.class).getResultList();
    }

    public void persist(Course course) {
        em.persist(course);
    }

    public Course findById(int id) {
        return em.find(Course.class, id);
    }
}
