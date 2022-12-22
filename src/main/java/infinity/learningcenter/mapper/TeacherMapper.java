package infinity.learningcenter.mapper;

import infinity.learningcenter.dao.Teacher;
import infinity.learningcenter.dto.TeacherDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher toEntity(TeacherDto teacherDto);
    TeacherDto toDto(Teacher teacher);
}
