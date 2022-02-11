package restapi_demo.service;

import org.springframework.stereotype.Service;
import restapi_demo.domain.Student;
import restapi_demo.domain.dto.StudentResponseDTO;

@Service
public interface StudentService {
    StudentResponseDTO insertStudent(Student student);
    Iterable<StudentResponseDTO> findAll();
    Iterable<StudentResponseDTO> findByFirstName(String name);
    Iterable<StudentResponseDTO> findByLastName(String name);
    StudentResponseDTO findById(long id);


}
