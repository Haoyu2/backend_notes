package restapi_demo.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import restapi_demo.domain.Student;
import restapi_demo.domain.dto.StudentResponseDTO;
import restapi_demo.repository.StudentRepository;
import restapi_demo.service.StudentService;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceJPA implements StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentServiceJPA(@Qualifier("studentRepositoryJPA") StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentResponseDTO insertStudent(Student student) {
        repository.save(student);
        return new StudentResponseDTO(student.getFirst_name() + student.getLast_name());
    }

    @Override
    public Iterable<StudentResponseDTO> findAll() {
        return toStudentDTOIter(repository.findAll());
    }

    @Override
    public Iterable<StudentResponseDTO> findByFirstName(String name) {
        return toStudentDTOIter(repository.findByFirstName(name));
    }

    @Override
    public Iterable<StudentResponseDTO> findByLastName(String name) {
        return toStudentDTOIter(repository.findByLastName(name));
    }

    @Override
    public StudentResponseDTO findById(long id) {
        Student student = repository.findById(id);
        return new StudentResponseDTO(student.getFirst_name() + student.getLast_name());
    }


    private Iterable<StudentResponseDTO> toStudentDTOIter(Iterable<Student> students){
        return StreamSupport
                .stream(students.spliterator(), true)
                .map(student -> new StudentResponseDTO(student.getFirst_name() + student.getLast_name()))
                .collect(Collectors.toList());
    }
}
