package lab1.services;

import lab1.jpa.entities.Lecturer;

import java.io.Serializable;

public interface NameCreator extends Serializable {
    String generateName(Lecturer lecturer);
}
