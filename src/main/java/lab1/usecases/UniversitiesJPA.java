package lab1.usecases;

import lab1.jpa.entities.University;
import lab1.jpa.persistence.UniversitiesDAO;
import lab1.services.UniversityNameGenerator;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class UniversitiesJPA implements Serializable {
    @Inject
    private UniversitiesDAO universities;

    @Getter
    @Setter
    private University newUniversity = new University();

    @Inject
    private UniversityNameGenerator universityNameGenerator;
    @Getter
    private List<University> allUniversities;

    @PostConstruct
    public void init() {
        loadAllUniversities();
    }

    public University findById(int id) {
        return universities.findById(id);
    }

    @Transactional
    public void add() {
        newUniversity.setName(universityNameGenerator.generateName(newUniversity.getName()));
        universities.persist(newUniversity);
    }

    private void loadAllUniversities() {
        allUniversities = universities.loadAll();
    }
}