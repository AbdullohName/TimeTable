package infinity.learningcenter.dto;

import infinity.learningcenter.dao.Course;
import infinity.learningcenter.dao.CourseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.List;

/**
 * A DTO for the {@link CourseType} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseTypeDto {
    private Integer id;
    private String name;
    private List<Course> courseList;
}