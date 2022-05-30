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
        em.flush();
    }

    public Course findById(long id) {
        return em.find(Course.class, id);
    }

    public Course update(Course course) {
        Course c = em.merge(course);
        em.flush();
        return c;
    }

    public List<Course> findByLecturerId(int lecturerId) {
        return em.createNamedQuery("Course.findByLecturerId", Course.class).
                setParameter("lecturer", lecturerId).
                getResultList();
    }

}
