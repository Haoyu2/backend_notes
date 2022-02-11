package restapi_demo.repository;


import org.springframework.stereotype.Repository;
import orm.domain.TeacherStudent;
import restapi_demo.domain.Student;
import restapi_demo.domain.Teacher;
import restapi_demo.domain.TeacherStudentMapping;

@Repository
public interface TeacherStudentRepository {
    Iterable<TeacherStudent> findAll();
    Iterable<Teacher> findByStudent(Student student);
    Iterable<Student> findByTeacher(Teacher student);

    void save(TeacherStudentMapping ts);

}
