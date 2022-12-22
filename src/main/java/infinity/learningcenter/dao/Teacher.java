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
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "teacher_id_seq")
    private Integer id;
    private String name;
    private Boolean booked;
    private String workTime;
    private String about;
    private Integer courseId;
    @OneToMany(mappedBy = "teacherId")
    private List<Group> groups;
//    @ManyToMany
//    @JoinTable(name = "course_teacher",
//              joinColumns = {
//            @JoinColumn
//                    (name = "teacher_id")},
//            inverseJoinColumns = {
//                @JoinColumn(name = "course_id")
//            })
//    private List<Course> courseList;
}
