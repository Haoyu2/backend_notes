package restapi_demo.service;

import org.springframework.stereotype.Service;
import restapi_demo.domain.Teacher;
import restapi_demo.domain.dto.TeacherResponseDTO;

@Service
public interface TeacherService {
    TeacherResponseDTO insertTeacher(Teacher teacher);
    Iterable<TeacherResponseDTO> findAll();
    Iterable<TeacherResponseDTO> findByFirstName(String name);
    Iterable<TeacherResponseDTO> findByLastName(String name);
    TeacherResponseDTO findById(long id);

}
