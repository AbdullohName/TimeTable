package infinity.learningcenter.mapper;

import infinity.learningcenter.dao.Room;
import infinity.learningcenter.dto.RoomDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    Room toEntity(RoomDto roomDto);
    RoomDto toDto(Room room);
}
