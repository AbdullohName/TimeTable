package infinity.learningcenter.controller;

import infinity.learningcenter.dto.CourseTypeDto;
import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.service.CourseTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course-type")
@RequiredArgsConstructor
public class CourseTypeController {
    private final CourseTypeService service;

    @PostMapping
    public ResponseDto<String> add(@RequestBody @Valid CourseTypeDto courseTypeDto) {
        return service.add(courseTypeDto);
    }

    @GetMapping
    public ResponseDto<List<CourseTypeDto>> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseDto<CourseTypeDto> getById(@PathVariable("id") Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseDto<CourseTypeDto> update(@RequestBody CourseTypeDto courseTypeDto,@PathVariable("id") Integer id) {
        return service.update(courseTypeDto,id);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<String> deleteById(@PathVariable("id") Integer id) {
        return service.deleteById(id);
    }
}
