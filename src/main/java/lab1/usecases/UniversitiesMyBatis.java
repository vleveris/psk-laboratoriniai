package lab1.usecases;

import lab1.mybatis.model.University;
import lab1.mybatis.dao.UniversityMapper;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class UniversitiesMyBatis {
    @Inject
    private UniversityMapper universityMapper;

    @Getter
    @Setter
    private University newUniversity = new University();

    @Getter
    private List<University> allUniversities;

    @PostConstruct
    public void init() {
        loadAllUniversities();
    }

    public University findById(int id) {
        return universityMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void add() {
        universityMapper.insert(newUniversity);
    }

    private void loadAllUniversities() {
        allUniversities = universityMapper.selectAll();
    }
}