package lab1.usecases;

import lab1.jpa.entities.Lecturer;
import lab1.jpa.interceptors.LoggedInvocation;
import lab1.jpa.persistence.LecturersDAO;
import lab1.services.NameCreator;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.List;


@Model
@LoggedInvocation
public class LecturersJPA {
    @Inject
    private LecturersDAO lecturers;

    @Getter
    @Setter
    private Lecturer newLecturer = new Lecturer();
    @Inject
    private NameCreator nameCreator;
    @Getter
    @Setter
    private Lecturer editLecturer = new Lecturer();

    @Getter
    private List<Lecturer> allLecturers;


    @PostConstruct
    public void init() {
        loadAllLecturers();
    }

    public Lecturer findById(int id) {
        return lecturers.findById(id);
    }

    @Transactional
    public void add() {
        newLecturer.setName(nameCreator.generateName(newLecturer));
        lecturers.persist(newLecturer);
    }

    private void loadAllLecturers() {
        allLecturers = lecturers.loadAll();
        if (allLecturers.size() >= 1) {
            editLecturer = allLecturers.get(0);
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String saveChanges() throws InterruptedException {
        editLecturer.setName(nameCreator.generateName(editLecturer));
        System.out.println("5 seconds sleep");
        Thread.sleep(5000);
        try {
            lecturers.update(editLecturer);
            return "lecturers?faces-redirect=true";
        } catch (OptimisticLockException e) {
            System.out.println("Optimistic lock exception:\n" + e);
            return "edit-lecturer?faces-redirect=true" + "&error=optimistic-lock-exception";
        }
    }

}

