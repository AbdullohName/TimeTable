package infinity.learningcenter.controller;

import infinity.learningcenter.dto.CourseDto;
import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService service;
    @PostMapping
    public ResponseDto<String> add(@RequestBody @Valid CourseDto courseDto) {
        return service.add(courseDto);
    }
    @GetMapping
    public ResponseDto<List<CourseDto>> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public ResponseDto<CourseDto> getById(@PathVariable("id") Integer id) {
        return service.getById(id);
    }
    @GetMapping("/by-name")
    public ResponseDto<CourseDto> getByName(@RequestParam @NotBlank(message = "name must be not null") String name) {
        return service.getByName(name);
    }
    @PutMapping("/{id}")
    public ResponseDto<CourseDto> update(@RequestBody CourseDto courseDto, @PathVariable("id") Integer id) {
        return service.update(courseDto,id);
    }
    @DeleteMapping("/{id}")
    public ResponseDto<String> deleteById(@PathVariable Integer id) {
        return service.deleteById(id);
    }
}
