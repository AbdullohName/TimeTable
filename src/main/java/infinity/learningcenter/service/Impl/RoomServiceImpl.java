package infinity.learningcenter.service.Impl;

import infinity.learningcenter.dao.Group;
import infinity.learningcenter.dao.Room;
import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.dto.RoomDto;
import infinity.learningcenter.dto.WeekTableDto;
import infinity.learningcenter.mapper.RoomMapper;
import infinity.learningcenter.repository.RoomRepository;
import infinity.learningcenter.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public ResponseDto<WeekTableDto> getByWeekId(Integer id) {
        Integer weekId = 2;
        WeekTableDto weekTableDto = new WeekTableDto();
        weekTableDto.setWeekName(generateIdByWeek(id));
        if(id % 2 != 0) {
            weekId = 1;
        }
        List<Room> rooms = repository.findAll();
        Integer finalWeekId = weekId;
        for(int i = 0; i < rooms.size(); i++) {
            rooms.get(i).setGroupList(rooms.get(i).getGroupList().stream()
                    .filter(item -> item.getWeekId().equals(finalWeekId))
                    .collect(Collectors.toList()));
        }
        List<RoomDto> roomDtoList = filter(rooms);
        weekTableDto.setRooms(roomDtoList);
        return ResponseDto.<WeekTableDto>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(weekTableDto)
                .build();
    }

    @Override
    public ResponseDto<List<WeekTableDto>> getOneWeek() {  // 1 haftalik darsliklarni royxatini chiqarish
        List<Room> rooms = repository.findAll();
        List<Room> roomsId1 = new ArrayList<>();
        List<Room> roomsId2 = new ArrayList<>();
        for(Room room : rooms) {
            Room roomId1 = map(room);
            roomId1.getGroupList().clear();
            Room roomId2 = map(room);
            roomId2.getGroupList().clear();
            for(Group group: room.getGroupList()) {
                if(group.getWeekId().equals(1)) {
                    roomId1.getGroupList().add(group);
                } else roomId2.getGroupList().add(group);
            }
            if(!roomId1.getGroupList().isEmpty()) roomsId1.add(roomId1);
            if(!roomId2.getGroupList().isEmpty()) roomsId2.add(roomId2);
        }
        List<RoomDto> roomDtoList1 = roomsId1.stream()
                .map(mapper::toDto).toList();
        List<RoomDto> roomDtoList2 = roomsId2.stream()
                .map(mapper::toDto).toList();

        List<WeekTableDto> weekTableDtoList = new ArrayList<>(7);
        for(int i = 1; i < 7; i++) {
            WeekTableDto weekTableDto = new WeekTableDto();
            if(i % 2 != 0) {
                weekTableDto = new WeekTableDto(generateIdByWeek(i),roomDtoList1);
            } else weekTableDto = new WeekTableDto(generateIdByWeek(i),roomDtoList2);
            weekTableDtoList.add(weekTableDto);
        }
        return ResponseDto.<List<WeekTableDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(weekTableDtoList)
                .build();
    }

    public String generateIdByWeek(Integer id) {   // id boyicha hafta kunlarini qaytariw
        switch (id) {
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
        }
        return "null";
    }
    public List<RoomDto> filter(List<Room> rooms) {       // bosh bolgan honalarni ochirib tawlaw
        return rooms.stream()
                .map(mapper::toDto)
                .filter(item -> !item.getGroupList().isEmpty())
                .collect(Collectors.toList());
    }
    public Room map(Room room) {
        Room export = new Room();
        export.setId(room.getId());
        export.setName(room.getName());
        export.setBooked(room.getBooked());
        export.setGroupList(new ArrayList<>());
        return export;
    }
}
