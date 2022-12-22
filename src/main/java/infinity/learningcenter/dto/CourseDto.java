package infinity.learningcenter.dto;

import infinity.learningcenter.dao.Course;
import infinity.learningcenter.dao.Group;
import infinity.learningcenter.dao.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * A DTO for the {@link Course} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private Integer id;
    @NotBlank(message = "Field must not be empty")
    private String name;
    private Integer typeId;
    private String courseDate;
    private List<Teacher> teachers;
    private List<Group> groups;
}