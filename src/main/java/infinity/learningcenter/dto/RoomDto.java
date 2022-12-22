package infinity.learningcenter.dto;

import infinity.learningcenter.dao.Group;
import infinity.learningcenter.dao.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link Room} entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Integer id;
    private String name;
    private Boolean booked;
    private List<Group> groupList;
}