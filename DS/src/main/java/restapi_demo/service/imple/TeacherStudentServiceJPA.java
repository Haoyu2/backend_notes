package restapi_demo.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import orm.domain.TeacherStudent;
import restapi_demo.domain.Teacher;
import restapi_demo.domain.TeacherStudentMapping;
import restapi_demo.domain.dto.TeacherResponseDTO;
import restapi_demo.domain.dto.TeacherStudentDTO;
import restapi_demo.repository.TeacherStudentRepository;
import restapi_demo.service.TeacherStudentService;

@Service
public class TeacherStudentServiceJPA implements TeacherStudentService {
    private TeacherStudentRepository repository;

    @Autowired
    public TeacherStudentServiceJPA(
            @Qualifier("teacherStudentRepositoryJPA") TeacherStudentRepository repository) {
        this.repository = repository;
    }

    public TeacherStudentDTO insertTeacherStudent(TeacherStudentMapping ts) {
        repository.save(ts);
        return new TeacherStudentDTO(ts);
    }
}
