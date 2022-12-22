package infinity.learningcenter.service.Impl;

import infinity.learningcenter.dao.Teacher;
import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.dto.TeacherDto;
import infinity.learningcenter.mapper.TeacherMapper;
import infinity.learningcenter.repository.TeacherRepository;
import infinity.learningcenter.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;
    private final TeacherMapper mapper;
    @Override
    public ResponseDto<String> add(TeacherDto teacherDto) {
        teacherDto.setBooked(false);
        repository.save(mapper.toEntity(teacherDto));
        return ResponseDto.<String>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data("Successfully saved!")
                .build();
    }

    @Override
    public ResponseDto<List<TeacherDto>> getAll() {
        return ResponseDto.<List<TeacherDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(repository.findAll().stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public ResponseDto<List<TeacherDto>> getByStatus(Boolean status) {
        return ResponseDto.<List<TeacherDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(repository.getTeachersByStatus(status).stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public ResponseDto<TeacherDto> getById(Integer id) {
        Optional<Teacher> optional = repository.findById(id);
        if(optional.isPresent()) {
            return ResponseDto.<TeacherDto>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data(mapper.toDto(optional.get()))
                    .build();
        }
        return ResponseDto.<TeacherDto>builder()
                .code(-3)
                .message("id not found")
                .success(false)
                .build();
    }

    @Override
    public ResponseDto<TeacherDto> getByName(String name) {
        Optional<Teacher> optional = repository.findFirstByName(name);
        if(optional.isPresent()) {
            return ResponseDto.<TeacherDto>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data(mapper.toDto(optional.get()))
                    .build();
        }
        return ResponseDto.<TeacherDto>builder()
                .code(-3)
                .message("Not found")
                .success(false)
                .build();
    }

    @Override
    public ResponseDto<TeacherDto> update(TeacherDto teacherDto, Integer id) {
        if(repository.existsById(id)) {
            Teacher teacher = mapper.toEntity(teacherDto);
            teacher.setId(id);
            repository.save(teacher);
            return ResponseDto.<TeacherDto>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data(mapper.toDto(teacher))
                    .build();
        }
        return ResponseDto.<TeacherDto>builder()
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
