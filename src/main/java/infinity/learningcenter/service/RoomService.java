package infinity.learningcenter.service;

import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.dto.RoomDto;

import java.util.List;

public interface RoomService {
    ResponseDto<String> add(RoomDto roomDto);
    ResponseDto<List<RoomDto>> getAll();
    ResponseDto<RoomDto> getById(Integer id);
    ResponseDto<RoomDto> getByName(String name);
    ResponseDto<List<RoomDto>> getByStatus(Boolean isEmpty);
    ResponseDto<RoomDto> update(RoomDto roomDto,Integer id);
    ResponseDto<String> deleteById(Integer id);
}
