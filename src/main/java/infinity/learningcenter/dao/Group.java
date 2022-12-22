package infinity.learningcenter.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "group_id_seq")
    private Integer id;
    private String name;
    private Integer courseId;
    private Integer teacherId;
    private Integer roomId;
    private String date;
    private Integer sumPupil;
    private Integer weekId;
}
