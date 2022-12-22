package infinity.learningcenter.mapper;

import infinity.learningcenter.dao.Group;
import infinity.learningcenter.dto.GroupDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    Group toEntity(GroupDto groupDto);
    GroupDto toDto(Group group);
}
