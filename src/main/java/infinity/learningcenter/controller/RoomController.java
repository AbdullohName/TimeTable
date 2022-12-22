package infinity.learningcenter.controller;

import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.dto.RoomDto;
import infinity.learningcenter.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService service;
    @PostMapping
    public ResponseDto<String> add(@RequestBody @Valid RoomDto roomDto) {
        return service.add(roomDto);
    }
    @GetMapping
    public ResponseDto<List<RoomDto>> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public ResponseDto<RoomDto> getById(Integer id) {
        return service.getById(id);
    }
    @GetMapping("/by-name")
    public ResponseDto<RoomDto> getByName(@RequestParam @NotBlank(message = "Field must not be empty") String name) {
        return service.getByName(name);
    }
    @GetMapping("/by-status/{isEmpty}")
    public ResponseDto<List<RoomDto>> getByStatus(@PathVariable("isEmtpy") Boolean isEmpty) {
        return service.getByStatus(isEmpty);
    }
    @PutMapping("/{id}")
    public ResponseDto<RoomDto> update(@RequestBody RoomDto roomDto,@PathVariable("id") Integer id) {
        return service.update(roomDto,id);
    }
    @DeleteMapping("/{id}")
    public ResponseDto<String> deleteById(@PathVariable("id") Integer id) {
        return service.deleteById(id);
    }
}
