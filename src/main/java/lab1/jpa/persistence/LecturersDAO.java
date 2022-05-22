package lab1.jpa.persistence;

import lab1.jpa.entities.Lecturer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class LecturersDAO {
    @Inject
    private EntityManager em;

    public List<Lecturer> loadAll() {
        return em.createNamedQuery("Lecturer.findAll", Lecturer.class).getResultList();
    }

    public void persist(Lecturer lecturer) {
        em.persist(lecturer);
    }

    public Lecturer findById(int id) {
        return em.find(Lecturer.class, id);
    }
}