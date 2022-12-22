package infinity.learningcenter.service.Impl;

import infinity.learningcenter.dao.CourseType;
import infinity.learningcenter.dto.CourseTypeDto;
import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.mapper.CourseTypeMapper;
import infinity.learningcenter.repository.CourseTypeRepository;
import infinity.learningcenter.service.CourseTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseTypeServiceImpl implements CourseTypeService {

    private final CourseTypeRepository repository;
    private final CourseTypeMapper mapper;

    @Override
    public ResponseDto<String> add(CourseTypeDto courseTypeDto) {
        repository.save(mapper.toEntity(courseTypeDto));
        return ResponseDto.<String>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data("Successfully saved")
                .build();
    }

    @Override
    public ResponseDto<List<CourseTypeDto>> getAll() {
        List<CourseType> courseTypes = repository.findAll();
        List<CourseTypeDto> courseTypeDtoList = courseTypes.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseDto.<List<CourseTypeDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(courseTypeDtoList)
                .build();
    }

    @Override
    public ResponseDto<CourseTypeDto> getById(Integer id) {
        Optional<CourseType> optional =  repository.findById(id);
        if(optional.isPresent()) {
            return ResponseDto.<CourseTypeDto>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data(mapper.toDto(optional.get()))
                    .build();
        }
        return ResponseDto.<CourseTypeDto>builder()
                .code(-3)
                .message("Not found")
                .success(false)
                .build();
    }

    @Override
    public ResponseDto<CourseTypeDto> update(CourseTypeDto courseTypeDto, Integer id) {
        if(repository.existsById(id)) {
            CourseType courseType = mapper.toEntity(courseTypeDto);
            courseType.setId(id);
            repository.save(courseType);
            return ResponseDto.<CourseTypeDto>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data(mapper.toDto(courseType))
                    .build();
        }
        return ResponseDto.<CourseTypeDto>builder()
                .code(-4)
                .message("Not found")
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
                .success(false)
                .message("OK")
                .build();
    }
}
