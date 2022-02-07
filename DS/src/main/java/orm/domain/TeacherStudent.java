package orm.domain;

import lombok.*;

import javax.persistence.*;

@Table(name = "teacher_student")
@Entity

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded= true)
@NoArgsConstructor
@AllArgsConstructor
public class TeacherStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_id")
    private Student stu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_id")
    private Teacher teacher;
}
