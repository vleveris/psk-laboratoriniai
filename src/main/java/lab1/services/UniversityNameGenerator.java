package lab1.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UniversityNameGenerator implements NameGenerator {

    public String generateName(String name) {

        String generatedName = name + " University";
        return generatedName;
    }

}
