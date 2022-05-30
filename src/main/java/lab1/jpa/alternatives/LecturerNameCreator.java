package lab1.jpa.alternatives;

import lab1.jpa.entities.Lecturer;
import lab1.services.NameCreator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@ApplicationScoped
public class LecturerNameCreator implements NameCreator {
    @Override
    public String generateName(Lecturer lecturer) {
        if (lecturer == null) {
            throw new NullPointerException("Lecturer is null");
        }
        return lecturer.getFirstName() + " " + lecturer.getLastName();
    }
}
