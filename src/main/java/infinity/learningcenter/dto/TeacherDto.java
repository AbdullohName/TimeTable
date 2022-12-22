package infinity.learningcenter.dto;

import infinity.learningcenter.dao.Course;
import infinity.learningcenter.dao.Group;
import infinity.learningcenter.dao.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A DTO for the {@link Teacher} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDto {
    private Integer id;
    private String name;
    private Boolean booked;
    private String workTime;
    private String about;
    private List<Group> groups;
    private Integer courseId;
//    private List<Course> courseList;
}