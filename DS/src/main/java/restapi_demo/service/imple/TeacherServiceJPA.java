package restapi_demo.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import restapi_demo.domain.Teacher;
import restapi_demo.domain.dto.TeacherResponseDTO;
import restapi_demo.repository.TeacherRepository;
import restapi_demo.service.TeacherService;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeacherServiceJPA implements TeacherService {
    private final TeacherRepository repository;

    @Autowired
    public TeacherServiceJPA(@Qualifier("teacherRepositoryJPA") TeacherRepository repository) {
        this.repository = repository;
    }

    @Override
    public TeacherResponseDTO insertTeacher(Teacher teacher) {
        repository.save(teacher);
        return new TeacherResponseDTO(teacher.getFirst_name() + teacher.getLast_name());
    }

    @Override
    public Iterable<TeacherResponseDTO> findAll() {
        return toTeacherDTOIter(repository.findAll());
    }

    @Override
    public Iterable<TeacherResponseDTO> findByFirstName(String name) {
        return toTeacherDTOIter(repository.findByFirstName(name));
    }

    @Override
    public Iterable<TeacherResponseDTO> findByLastName(String name) {
        return toTeacherDTOIter(repository.findByLastName(name));
    }

    @Override
    public TeacherResponseDTO findById(long id) {
        Teacher teacher = repository.findById(id);
        return new TeacherResponseDTO(teacher.getFirst_name() + teacher.getLast_name());
    }


    private Iterable<TeacherResponseDTO> toTeacherDTOIter(Iterable<Teacher> teachers){
        return StreamSupport
                .stream(teachers.spliterator(), true)
                .map(teacher -> new TeacherResponseDTO(teacher.getFirst_name() + teacher.getLast_name()))
                .collect(Collectors.toList());
    }
}
