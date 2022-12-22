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
@Table(name = "course_type")
public class CourseType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "course_type_id_seq")
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "typeId")
    private List<Course> courseList;
}
