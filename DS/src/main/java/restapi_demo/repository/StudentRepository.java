package restapi_demo.repository;


import org.springframework.stereotype.Repository;
import restapi_demo.domain.Student;

@Repository
public interface StudentRepository {

    Iterable<Student> findAllPagination(int size, int index);
    Iterable<Student> findAll();
    Iterable<Student> findByFirstName(String first_name);
    Iterable<Student> findByLastName(String last_name);

    Student findById(long id);

    void save(Student student);


}
