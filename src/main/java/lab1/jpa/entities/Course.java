package lab1.jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Course.findAll", query = "select c from Course c")
})
@Getter
@Setter
@Table
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    private Lecturer lecturer;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Course o)) {
            return false;
        }
        return o.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}

