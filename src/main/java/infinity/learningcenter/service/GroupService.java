package infinity.learningcenter.service;

import infinity.learningcenter.dto.GroupDto;
import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.dto.WeekTableDto;

import java.util.List;

public interface GroupService {
    ResponseDto<String> add(GroupDto groupDto);
    ResponseDto<List<GroupDto>> getAll();
    ResponseDto<GroupDto> getById(Integer id);
    ResponseDto<GroupDto> getByName(String name);
    ResponseDto<List<GroupDto>> getByWeekId(Integer id);
    ResponseDto<GroupDto> update(GroupDto groupDto,Integer id); //94 656 07 00
    ResponseDto<String> deleteById(Integer id);
}
