package infinity.learningcenter.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "course_id_seq")
    private Integer id;
    @Column(unique = true)
    private String name;
    private Integer typeId;
    @OneToMany(mappedBy = "courseId")
    private List<Teacher> teachers;
    @OneToMany(mappedBy = "courseId")
    private List<Group> groups;
}
