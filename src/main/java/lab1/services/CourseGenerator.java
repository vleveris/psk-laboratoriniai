package lab1.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class CourseGenerator implements ICourseGenerator, Serializable {

    public long generateId() {
        try {
            Random rand = new Random();
            long id = rand.nextLong(1000000);

            Thread.sleep(3000);
            return id;
        } catch (InterruptedException e) {
            return 0;
        }


    }
}
