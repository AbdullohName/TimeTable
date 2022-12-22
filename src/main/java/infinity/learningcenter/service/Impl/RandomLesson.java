package infinity.learningcenter.service.Impl;

import infinity.learningcenter.dao.Group;
import infinity.learningcenter.dao.Room;
import infinity.learningcenter.dao.Teacher;
import infinity.learningcenter.dto.Information;
import infinity.learningcenter.dto.ResponseDto;
import infinity.learningcenter.repository.GroupRepository;
import infinity.learningcenter.repository.RoomRepository;
import infinity.learningcenter.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RandomLesson {
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final RoomRepository roomRepository;

    HashMap<String,Group> roomGroup1 = new HashMap<>();       // 1 - week room ichidagi group uchun
    HashMap<String,Group> roomGroup2 = new HashMap<>();       // 2 - week room ichidagi group uchun

    HashMap<String,Group> teacherGroup1 = new HashMap<>();       // 1 - week teacher ichidagi group uchun
    HashMap<String,Group> teacherGroup2 = new HashMap<>();       // 2 - week teacher ichidagi group uchun

    HashMap<Integer,Teacher> teacherHashMap = new HashMap<>();   // Ustozla uchun har bir ustozning gruppasi bor yoqligini tekshiriish
    String dissMissedGroups;

    public ResponseDto<String> random(Information information) {
        clearHashMap();
        List<Group> groups = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        for(Integer integer: information.getGroups()) {
            Optional<Group> optional = groupRepository.findById(integer);
            if(!optional.isEmpty() && optional.get().getTeacherId() == null) {
                groups.add(optional.get());
            }
        }
        for(Integer integer: information.getRooms()) {
            Optional<Room> optional = roomRepository.findById(integer);
            if(!optional.isEmpty() && optional.get().getGroupList().size() < 8) {
                rooms.add(optional.get());
            } else return ResponseDto.<String>builder()
                    .code(-2)
                    .message("Room is not available")
                    .success(false)
                    .build();
        }
        for(Integer integer: information.getTeachers()) {
            Optional<Teacher> optional = teacherRepository.findById(integer);
            if(!optional.isEmpty() && optional.get().getGroups().size() < 8) {
                teacherHashMap.put(optional.get().getCourseId(),optional.get());
            } else return ResponseDto.<String>builder()
                    .code(-2)
                    .message("Teacher is not available")
                    .success(false)
                    .build();
        }
        int roomIndex = 0;
        int k = 0;
        // grouplarni ustozlar va honalarga bolish
        for(Group group : groups) {
            if(rooms.get(roomIndex).getGroupList().size() > 7 && rooms.size() > roomIndex + 1) roomIndex++;
            if(teacherHashMap.containsKey(group.getCourseId()) && teacherHashMap.get(group.getCourseId()).getGroups().size() < 8) {
                if(randomLesson(group,teacherHashMap.get(group.getCourseId()),rooms.get(roomIndex))) {
                    teacherHashMap.get(group.getCourseId()).getGroups().add(group);
                    rooms.get(roomIndex).getGroupList().add(group);
                    k++;
                } else {
                    dissMissedGroups += group.getId() + group.getName() + "\n";
                    return ResponseDto.<String>builder()
                            .code(-2)
                            .message("Rooms are not available")
                            .success(false)
                            .data(dissMissedGroups)
                            .build();
                }


            }
        }

        if(groups.size() != 0 && k > 0 ) {
            return ResponseDto.<String>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .data(dissMissedGroups)
                    .build();
        }
        return ResponseDto.<String>builder()
                .code(-2)
                .message("Groups already available")
                .success(false)
                .data(dissMissedGroups)
                .build();
    }



    // Ustoz va xonaga grouplani taqsimlash
    public Boolean randomLesson(Group group, Teacher teacher, Room room) {
        for(Group group1: room.getGroupList()) {
            if(group1.getWeekId().equals(1)) roomGroup1.put(group1.getDate(),group1);
            else roomGroup2.put(group1.getDate(),group1);
        }
        for(Group group1: teacher.getGroups()) {
            if(group1.getWeekId().equals(1)) teacherGroup1.put(group1.getDate(),group1);
            else teacherGroup2.put(group1.getDate(),group1);
        }
        if(updateToDb(group,teacher,room,roomGroup1,teacherGroup1,1)) return true;
        else if(updateToDb(group,teacher,room,roomGroup2,teacherGroup2,2)) return true;
        return false;
    }


    // Smena boyicha bizaga grouplani bolib beradi
    public Boolean updateToDb(Group group, Teacher teacher, Room room,HashMap<String,Group> roomGroup,HashMap<String,Group> teacherGroup,Integer weekId) {
        if(!roomGroup.containsKey("09:00 - 11:00") && !teacherGroup.containsKey("09:00 - 11:00")) {
            group.setDate("09:00 - 11:00");
            group.setTeacherId(teacher.getId());
            group.setRoomId(room.getId());
            group.setWeekId(weekId);
            groupRepository.save(group);
            changeHashMap("09:00 - 11:00",weekId,group);
            return true;
        } else if (!roomGroup.containsKey("11:00 - 13:00") && !teacherGroup.containsKey("11:00 - 13:00")) {
            group.setDate("11:00 - 13:00");
            group.setTeacherId(teacher.getId());
            group.setRoomId(room.getId());
            group.setWeekId(weekId);
            groupRepository.save(group);
            changeHashMap("11:00 - 13:00",weekId,group);
            return true;
        } else if (!roomGroup.containsKey("14:00 - 16:00") && !teacherGroup.containsKey("14:00 - 16:00")) {
            group.setDate("14:00 - 16:00");
            group.setTeacherId(teacher.getId());
            group.setRoomId(room.getId());
            group.setWeekId(weekId);
            groupRepository.save(group);
            changeHashMap("14:00 - 16:00",weekId,group);
            return true;
        } else if (!roomGroup.containsKey("16:00 - 18:00") && !teacherGroup.containsKey("16:00 - 18:00")) {
            group.setDate("16:00 - 18:00");
            group.setTeacherId(teacher.getId());
            group.setRoomId(room.getId());
            group.setWeekId(weekId);
            groupRepository.save(group);
            changeHashMap("16:00 - 18:00",weekId,group);
            return true;
        }
        return false;
    }
    // Malumotlarni hafta kunlari boyicha bolib hashmaplarni toldirish
    public void changeHashMap(String date,Integer weekId,Group group) {
        if(weekId == 1) {
            roomGroup1.put(date,group);
            teacherGroup1.put(date,group);
        } else {
            roomGroup2.put(date,group);
            teacherGroup2.put(date,group);
        }
    }
    // har bitta request boganda malumotlarni ochirish
    public void clearHashMap(){
        roomGroup1.clear();
        roomGroup2.clear();
        teacherGroup1.clear();
        teacherGroup2.clear();
        teacherHashMap.clear();
        dissMissedGroups = "";
    }
}
