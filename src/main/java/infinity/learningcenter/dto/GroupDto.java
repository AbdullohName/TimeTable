package infinity.learningcenter.dto;

import infinity.learningcenter.dao.Course;
import infinity.learningcenter.dao.Group;
import infinity.learningcenter.dao.Room;
import infinity.learningcenter.dao.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link Group} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupDto {
    private Integer id;
    @NotBlank(message = "Field must not be empty")
    private String name;
    @NotNull
    private Integer courseId;
    private Integer teacherId;
    private Integer roomId;
    private String date;
    @NotBlank(message = "Field must not be empty")
    private Integer sumPupil;
    private Integer weekId;
}