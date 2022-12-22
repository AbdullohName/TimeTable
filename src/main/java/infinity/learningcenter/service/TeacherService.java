package infinity.learningcenter.service;

import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    ResponseDto<String> add(TeacherDto teacherDto);
    ResponseDto<List<TeacherDto>> getAll();
    ResponseDto<List<TeacherDto>> getByStatus(Boolean status);
    ResponseDto<TeacherDto> getById(Integer id);
    ResponseDto<TeacherDto> getByName(String name);
    ResponseDto<TeacherDto> update(TeacherDto teacherDto, Integer id);
    ResponseDto<String> deleteById(Integer id);
}
