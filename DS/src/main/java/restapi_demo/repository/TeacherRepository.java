package restapi_demo.repository;


import org.springframework.stereotype.Repository;
import restapi_demo.domain.Teacher;

@Repository
public interface TeacherRepository {
    Iterable<Teacher> findAll();
    Iterable<Teacher> findByFirstName(String first_name);
    Iterable<Teacher> findByLastName(String last_name);

    Teacher findById(long id);

    void save(Teacher student);

}
