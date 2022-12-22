package infinity.learningcenter.mapper;

import infinity.learningcenter.dao.CourseType;
import infinity.learningcenter.dto.CourseTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseTypeMapper {
    CourseTypeDto toDto(CourseType courseType);
    CourseType toEntity(CourseTypeDto courseTypeDto);
}
