package infinity.learningcenter.service.Impl;

import infinity.learningcenter.dao.Course;
import infinity.learningcenter.dto.CourseDto;
import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.mapper.CourseMapper;
import infinity.learningcenter.repository.CourseRepository;
import infinity.learningcenter.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;
    private final CourseMapper mapper;


    @Override
    public ResponseDto<String> add(CourseDto courseDto) {
        try {
            repository.save(mapper.toEntity(courseDto));
        } catch (Exception e) {
            return ResponseDto.<String>builder()
                    .code(-4)
                    .message("Error")
                    .success(false)
                    .data(e.getMessage())
                    .build();
        }
        return ResponseDto.<String>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data("Successfully saved")
                .build();
    }

    @Override
    public ResponseDto<List<CourseDto>> getAll() {
        try {
            List<Course> courseList = repository.findAll();
            List<CourseDto> courseDtoList = courseList.stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
            return ResponseDto.<List<CourseDto>>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data(courseDtoList)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<CourseDto>>builder()
                    .code(-5)
                    .message("Error")
                    .success(false)
                    .build();
        }
    }

    @Override
    public ResponseDto<CourseDto> getById(Integer id) {
        Optional<Course> optional = repository.findById(id);
        if(optional.isEmpty()) {
            return ResponseDto.<CourseDto>builder()
                    .code(-4)
                    .message("Not Found")
                    .success(false)
                    .build();
        }
        return ResponseDto.<CourseDto>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(mapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<CourseDto> getByName(String name) {
        Optional<Course> optional = repository.findFirstByName(name);
        if(optional.isEmpty()) {
            return ResponseDto.<CourseDto>builder()
                    .code(-4)
                    .message("Not Found")
                    .success(false)
                    .build();
        }
        return ResponseDto.<CourseDto>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(mapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<CourseDto> update(CourseDto courseDto, Integer id) {
        try {
            Optional<Course> optional = repository.findById(id);
            if(optional.isPresent()) {
                Course course = mapper.toEntity(courseDto);
                course.setId(id);
                repository.save(course);
                return ResponseDto.<CourseDto>builder()
                        .code(0)
                        .message("OK")
                        .success(true)
                        .data(mapper.toDto(course))
                        .build();
            }
            return ResponseDto.<CourseDto>builder()
                    .code(-4)
                    .message("Id not found")
                    .success(false)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<CourseDto>builder()
                    .code(-5)
                    .message(e.getMessage())
                    .success(false)
                    .build();
        }
    }

    @Override
    public ResponseDto<String> deleteById(Integer id) {
        try {
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
                    .message("id not found")
                    .success(false)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<String>builder()
                    .code(-5)
                    .message(e.getMessage())
                    .success(false)
                    .build();
        }
    }
}
