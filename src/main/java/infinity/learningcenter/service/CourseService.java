package infinity.learningcenter.service;

import infinity.learningcenter.dto.CourseDto;
import infinity.learningcenter.dto.ResponseDto;

import java.util.List;

public interface CourseService {
    ResponseDto<String> add(CourseDto courseDto);
    ResponseDto<List<CourseDto>> getAll();
    ResponseDto<CourseDto> getById(Integer id);
    ResponseDto<CourseDto> getByName(String name);
    ResponseDto<CourseDto> update(CourseDto courseDto,Integer id);
    ResponseDto<String> deleteById(Integer id);
}
