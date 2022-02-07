package orm.domain;

import lombok.*;

import javax.persistence.*;

@Table(name = "teacher")
@Entity

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded= true)
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Column
    private String firstName;

    @Column
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
