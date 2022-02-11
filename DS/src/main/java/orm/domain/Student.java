package orm.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "student")
@Entity

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded= true)
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Column
    private String firstName;

    @Column
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


//    @OneToMany(mappedBy = "stu", fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = false)
//    private List<TeacherStudent> teacherStudents = new ArrayList<>();
//
//
//    public void addTeachStudent(TeacherStudent ts){teacherStudents.add(ts);}





}
