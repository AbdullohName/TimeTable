package infinity.learningcenter.controller;

import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.dto.TeacherDto;
import infinity.learningcenter.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService service;
    @PostMapping
    public ResponseDto<String> add(@RequestBody @Valid TeacherDto teacherDto) {
        return service.add(teacherDto);
    }
    @GetMapping
    public ResponseDto<List<TeacherDto>> getAll() {
        return service.getAll();
    }
    @GetMapping("/by-status/{status}")
    public ResponseDto<List<TeacherDto>> getByStatus(@PathVariable("status") Boolean status) {
        return service.getByStatus(status);
    }
    @GetMapping("/by-id/{id}")
    public ResponseDto<TeacherDto> getById(@PathVariable("id") Integer id) {
        return service.getById(id);
    }
    @GetMapping("/by-name")
    public ResponseDto<TeacherDto> getByName(@RequestParam String name) {
        return service.getByName(name);
    }
    @PutMapping("/{id}")
    public ResponseDto<TeacherDto> update(@RequestBody TeacherDto teacherDto,@PathVariable("id") Integer id) {
        return service.update(teacherDto,id);
    }
    @DeleteMapping("/{id}")
    public ResponseDto<String> deleteById(@PathVariable("id") Integer id) {
        return service.deleteById(id);
    }
}
