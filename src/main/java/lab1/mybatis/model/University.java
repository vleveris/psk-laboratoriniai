package lab1.mybatis.model;
import java.util.Set;

public class University {
private Set<Lecturer> coursesWithLecturers;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.UNIVERSITY.ID
     *
     * @mbg.generated Sun May 22 01:44:44 EEST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.UNIVERSITY.NAME
     *
     * @mbg.generated Sun May 22 01:44:44 EEST 2022
     */
    private String name;

public Set<Lecturer> getCoursesWithLecturers()
{
return coursesWithLecturers;
}

public void setCoursesWithLecturers(Set<Lecturer> coursesWithLecturers)
{
this.coursesWithLecturers = coursesWithLecturers;
}

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.UNIVERSITY.ID
     *
     * @return the value of PUBLIC.UNIVERSITY.ID
     *
     * @mbg.generated Sun May 22 01:44:44 EEST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.UNIVERSITY.ID
     *
     * @param id the value for PUBLIC.UNIVERSITY.ID
     *
     * @mbg.generated Sun May 22 01:44:44 EEST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.UNIVERSITY.NAME
     *
     * @return the value of PUBLIC.UNIVERSITY.NAME
     *
     * @mbg.generated Sun May 22 01:44:44 EEST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.UNIVERSITY.NAME
     *
     * @param name the value for PUBLIC.UNIVERSITY.NAME
     *
     * @mbg.generated Sun May 22 01:44:44 EEST 2022
     */
    public void setName(String name) {
        this.name = name;
    }
}