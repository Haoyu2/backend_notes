package restapi_demo.domain;

import io.swagger.annotations.ApiModel;
import lombok.*;
import orm.domain.TeacherStudent;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity

public class Student {
    @Id
    @GeneratedValue
    private long id;

    @NotNull(message = "first name cannot be null")
    @Column(length = 15, nullable = false)
    @Size(min = 3, max = 15, message="Name must be at least 3 characters long")
    private String first_name;

    @Column(length = 15, nullable = false)
    @NotNull(message = "last name cannot be null")
    @Size(min = 3, max = 15, message="Name must be at least 3 characters long")
    private String last_name;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<TeacherStudentMapping> teacherStudentMappings = new ArrayList<>();

}
