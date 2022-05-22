package lab1.jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Lecturer.findAll", query = "select l from Lecturer l")
})
@Table
@Getter
@Setter
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "lecturer")
    private List<Course> courses;

    @ManyToMany(mappedBy = "coursesWithLecturers")
    private Set<University> universities;

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Lecturer o)) {
            return false;
        }
        return o.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
