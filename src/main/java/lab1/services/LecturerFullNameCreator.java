package lab1.services;

import lab1.jpa.entities.Lecturer;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class LecturerFullNameCreator implements Serializable {
    /**
     * Assigns full name based on first and last names (JPA version).
     *
     * @param lecturer --> full names should be assigned
     */
    public void createFullName(Lecturer lecturer) {
        if (lecturer == null) {
            throw new NullPointerException("Lecturer is null");
        }
        String firstName = lecturer.getFirstName();
        if (firstName == null) {
            throw new NullPointerException("First name is null");
        }
        String lastName = lecturer.getLastName();
        if (lastName == null) {
            throw new NullPointerException("LastName is null");
        }

        lecturer.setName(generateName(firstName, lastName));
    }

    /**
     * Assigns full name based on first and last names (MyBatis version).
     *
     * @param lecturer --> full name should be assigned
     */
    public void createFullName(lab1.mybatis.model.Lecturer lecturer) {
        if (lecturer == null) {
            throw new NullPointerException("Lecturer is null");
        }
        String firstName = lecturer.getFirstname();
        if (firstName == null) {
            throw new NullPointerException("First name is null");
        }
        String lastName = lecturer.getLastname();
        if (lastName == null) {
            throw new NullPointerException("LastName is null");
        }

        lecturer.setName(generateName(firstName, lastName));
    }
    private String generateName(String firstName, String lastName) {
        return firstName + ' ' + lastName;
    }
}
