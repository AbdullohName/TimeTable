package infinity.learningcenter.service;

import infinity.learningcenter.dto.CourseTypeDto;
import infinity.learningcenter.dto.ResponseDto;

import java.util.List;

public interface CourseTypeService {
    ResponseDto<String> add(CourseTypeDto courseTypeDto);
    ResponseDto<List<CourseTypeDto>> getAll();
    ResponseDto<CourseTypeDto> getById(Integer id);
    ResponseDto<CourseTypeDto> update(CourseTypeDto courseTypeDto,Integer id);
    ResponseDto<String> deleteById(Integer id);
}
