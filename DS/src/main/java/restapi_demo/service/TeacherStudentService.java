package restapi_demo.service;

import restapi_demo.domain.TeacherStudentMapping;
import restapi_demo.domain.dto.TeacherStudentDTO;

public interface TeacherStudentService {
    TeacherStudentDTO insertTeacherStudent(TeacherStudentMapping ts);
}
