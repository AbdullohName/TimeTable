package infinity.learningcenter.controller;

import infinity.learningcenter.dto.GroupDto;
import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService service;
    @PostMapping
    public ResponseDto<String> add(@RequestBody GroupDto groupDto) {
        return service.add(groupDto);
    }
    @GetMapping
    public ResponseDto<List<GroupDto>> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public ResponseDto<GroupDto> getById(@PathVariable("id") Integer id) {
        return service.getById(id);
    }
    @GetMapping("/by-name")
    public ResponseDto<GroupDto> getByName(@RequestParam @NotBlank(message = "name must not be empty") String name) {
        return service.getByName(name);
    }
    @GetMapping("/week-id/{id}")
    public ResponseDto<List<GroupDto>> getByWeekId(@PathVariable("id") Integer id) {
        return service.getByWeekId(id);
    }
    @PutMapping("/{id}")
    public ResponseDto<GroupDto> update(@RequestBody GroupDto groupDto,@PathVariable("id") Integer id) {
        return service.update(groupDto,id);
    }
    @DeleteMapping("/{id}")
    public ResponseDto<String> deleteById(Integer id) {
        return service.deleteById(id);
    }
    @PostMapping("/clear")
    public ResponseDto<String> clearAll() {
        return service.clearAll();
    }
}
