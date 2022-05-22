package lab1.jpa.persistence;

import lab1.jpa.entities.University;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class UniversitiesDAO {
    @Inject
    private EntityManager em;

    public List<University> loadAll() {
        return em.createNamedQuery("University.findAll", University.class).getResultList();
    }

    public void persist(University university) {
        em.persist(university);
    }

    public void update(University university) {
        em.merge(university);
    }

    public University findById(int id) {
        return em.find(University.class, id);
    }
}
