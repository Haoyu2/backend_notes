package restapi_demo.repository.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import orm.domain.TeacherStudent;
import restapi_demo.domain.Student;
import restapi_demo.domain.Teacher;
import restapi_demo.domain.TeacherStudentMapping;
import restapi_demo.repository.TeacherStudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TeacherStudentRepositoryJPA implements TeacherStudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TeacherStudentRepositoryJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Iterable<TeacherStudent> findAll() {

        return entityManager.createQuery
                ("SELECT ts FROM TeacherStudent  ts", TeacherStudent.class)
                .getResultList();
    }

    @Override
    public Iterable<Teacher> findByStudent(Student student) {
        return null;
    }

    @Override
    public Iterable<Student> findByTeacher(Teacher teacher) {
        return null;
    }

    @Override
    public void save(TeacherStudentMapping ts) {
        entityManager.persist(ts);
    }
}
