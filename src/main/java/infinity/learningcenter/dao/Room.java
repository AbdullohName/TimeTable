package infinity.learningcenter.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "room_id_seq")
    private Integer id;
    private String name;
    private Boolean booked;
    @OneToMany(mappedBy = "roomId")
    private List<Group> groupList;
    public List<Group> getGroupList(){
        if(this.groupList == null) return new ArrayList<>();
        return this.groupList;
    }
}
