package infinity.learningcenter.mapper;

import infinity.learningcenter.dao.Course;
import infinity.learningcenter.dto.CourseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseDto courseDto);
    CourseDto toDto(Course course);
}
