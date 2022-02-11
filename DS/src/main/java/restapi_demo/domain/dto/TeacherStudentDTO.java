package restapi_demo.domain.dto;


import orm.domain.TeacherStudent;
import restapi_demo.domain.TeacherStudentMapping;

public class TeacherStudentDTO {
    private String ts;

    public TeacherStudentDTO(TeacherStudentMapping ts) {
        this.ts = String.format(
                "Teacher: %s %s  <==> Student: %s %s",
                ts.getTeacher().getFirst_name(), ts.getTeacher().getLast_name()
                , ts.getStudent().getFirst_name(), ts.getStudent().getLast_name());
    }
}
