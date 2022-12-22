package infinity.learningcenter.controller;

import infinity.learningcenter.dto.Information;
import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.service.Impl.RandomLesson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/random")
@RequiredArgsConstructor
public class RandomLessonController {
    private final RandomLesson service;
    @PostMapping
    public ResponseDto<String> random(@RequestBody @Valid Information information) {
        return service.random(information);
    }
}
