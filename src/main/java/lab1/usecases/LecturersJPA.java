package lab1.usecases;

import lab1.jpa.entities.Lecturer;
import lab1.jpa.persistence.LecturersDAO;
import lab1.services.LecturerFullNameCreator;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class LecturersJPA {
    @Inject
    private LecturersDAO lecturers;

    @Getter
    @Setter
    private Lecturer newLecturer = new Lecturer();

    @Getter
    private List<Lecturer> allLecturers;

    @Inject
    private LecturerFullNameCreator creator;

    @PostConstruct
    public void init() {
        loadAllLecturers();
    }

    public Lecturer findById(int id) {
        return lecturers.findById(id);
    }

    @Transactional
    public void add() {
        creator.createFullName(newLecturer);
        lecturers.persist(newLecturer);
    }

    private void loadAllLecturers() {
        allLecturers = lecturers.loadAll();
    }
}

