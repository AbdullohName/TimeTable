package infinity.learningcenter.service.Impl;

import infinity.learningcenter.dao.Room;
import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.dto.RoomDto;
import infinity.learningcenter.mapper.RoomMapper;
import infinity.learningcenter.repository.RoomRepository;
import infinity.learningcenter.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository repository;
    private final RoomMapper mapper;
    @Override
    public ResponseDto<String> add(RoomDto roomDto) {
        roomDto.setBooked(false);
        repository.save(mapper.toEntity(roomDto));
        return ResponseDto.<String>builder()
                .code(0)
                .success(true)
                .message("OK")
                .data("Successfully saved")
                .build();
    }

    @Override
    public ResponseDto<List<RoomDto>> getAll() {
        List<Room> rooms = repository.findAll();
        List<RoomDto> roomDtos = rooms.stream()
                .map(mapper::toDto).collect(Collectors.toList());
        return ResponseDto.<List<RoomDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(roomDtos)
                .build();
    }

    @Override
    public ResponseDto<RoomDto> getById(Integer id) {
        Optional<Room> optional = repository.findById(id);
        if(optional.isEmpty()) {
            return ResponseDto.<RoomDto>builder()
                    .code(-4)
                    .message("id not found")
                    .success(false)
                    .build();
        }
        return ResponseDto.<RoomDto>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(mapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<RoomDto> getByName(String name) {
        Optional<Room> optional = repository.findFirstByName(name);
        if(optional.isEmpty()) {
            return ResponseDto.<RoomDto>builder()
                    .code(-3)
                    .message("Not found")
                    .success(false)
                    .build();
        }
        return ResponseDto.<RoomDto>builder()
                .code(0)
                .message("Ok")
                .success(true)
                .data(mapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<List<RoomDto>> getByStatus(Boolean isEmpty) {
        List<Room> rooms = repository.getAllByStatus(isEmpty);
        List<RoomDto> roomDtoList = rooms.stream()
                .map(mapper::toDto).collect(Collectors.toList());
        return ResponseDto.<List<RoomDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(roomDtoList)
                .build();
    }

    @Override
    public ResponseDto<RoomDto> update(RoomDto roomDto, Integer id) {
        if(repository.existsById(id)) {
            Room room = mapper.toEntity(roomDto);
            room.setId(id);
            repository.save(room);
            return ResponseDto.<RoomDto>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data(mapper.toDto(room))
                    .build();
        }
        return ResponseDto.<RoomDto>builder()
                .code(-3)
                .message("id not found")
                .success(false)
                .build();
    }

    @Override
    public ResponseDto<String> deleteById(Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseDto.<String>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data("Successfully deleted")
                    .build();
        }
        return ResponseDto.<String>builder()
                .code(-3)
                .message("not found")
                .success(false)
                .data("id not found")
                .build();
    }
}
