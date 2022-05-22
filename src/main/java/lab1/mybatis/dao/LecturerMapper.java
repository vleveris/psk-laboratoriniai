package lab1.mybatis.dao;

import java.util.List;
import lab1.mybatis.model.Lecturer;
import lab1.mybatis.model.University;
import java.util.Set;

import org.mybatis.cdi.Mapper;

@Mapper
public interface LecturerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LECTURER
     *
     * @mbg.generated Sun May 22 01:44:44 EEST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LECTURER
     *
     * @mbg.generated Sun May 22 01:44:44 EEST 2022
     */
    int insert(Lecturer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LECTURER
     *
     * @mbg.generated Sun May 22 01:44:44 EEST 2022
     */
    Lecturer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LECTURER
     *
     * @mbg.generated Sun May 22 01:44:44 EEST 2022
     */
    List<Lecturer> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LECTURER
     *
     * @mbg.generated Sun May 22 01:44:44 EEST 2022
     */
    int updateByPrimaryKey(Lecturer record);
    Set<University> selectUniversitiesInLecturers(int lecturerId);
}