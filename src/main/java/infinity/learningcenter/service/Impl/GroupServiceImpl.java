package infinity.learningcenter.service.Impl;

import infinity.learningcenter.dao.Group;
import infinity.learningcenter.dto.GroupDto;
import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.mapper.GroupMapper;
import infinity.learningcenter.repository.GroupRepository;
import infinity.learningcenter.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository repository;
    private final GroupMapper mapper;
    @Override
    public ResponseDto<String> add(GroupDto groupDto) {
        repository.save(mapper.toEntity(groupDto));
        return ResponseDto.<String>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data("Successfully saved")
                .build();
    }

    @Override
    public ResponseDto<List<GroupDto>> getAll() {
        List<Group> groupList = repository.findAll();
        List<GroupDto> groupDtoList = groupList.stream()
                .map(mapper::toDto).collect(Collectors.toList());
        return ResponseDto.<List<GroupDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(groupDtoList)
                .build();
    }

    @Override
    public ResponseDto<GroupDto> getById(Integer id) {
        Optional<Group> optional = repository.findById(id);
        if(optional.isEmpty()) {
            return ResponseDto.<GroupDto>builder()
                    .code(-4)
                    .message("id not found")
                    .success(false)
                    .build();
        }
        return ResponseDto.<GroupDto>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(mapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<GroupDto> getByName(String name) {
        Optional<Group> optional = repository.findFirstByName(name);
        if(optional.isEmpty()) {
            return ResponseDto.<GroupDto>builder()
                    .code(-3)
                    .message("Name not found")
                    .success(false)
                    .build();
        }
        return ResponseDto.<GroupDto>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(mapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<List<GroupDto>> getByWeekId(Integer id) {
        List<Group> groupList = repository.getGroupByWeekIdd(id);
        List<GroupDto> groupDtoList = groupList.stream()
                .map(mapper::toDto).collect(Collectors.toList());
        return ResponseDto.<List<GroupDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(groupDtoList)
                .build();
    }

    @Override
    public ResponseDto<GroupDto> update(GroupDto groupDto,Integer id) {
        if(repository.existsById(id)) {
            Group group = mapper.toEntity(groupDto);
            group.setId(id);
            repository.save(group);
            return ResponseDto.<GroupDto>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data(mapper.toDto(group))
                    .build();
        }
        return ResponseDto.<GroupDto>builder()
                .code(-3)
                .message("Not Found")
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
                .message("Not found")
                .success(false)
                .build();
    }

    @Override
    public ResponseDto<String> clearAll() {
        try {
            repository.clearAll();
            return ResponseDto.<String>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data("Successfully")
                    .build();
        } catch (Exception e) {
            return ResponseDto.<String>builder()
                    .code(-2)
                    .message("Failed")
                    .success(false)
                    .data(e.getMessage())
                    .build();
        }
    }
}
