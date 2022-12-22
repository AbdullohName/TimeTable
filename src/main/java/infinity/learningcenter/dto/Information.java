package infinity.learningcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Information {
    @NotEmpty(message = "Field must not be empty")
    private List<Integer> groups;
    @NotEmpty(message = "Field must not be empty")
    private List<Integer> rooms;
    @NotEmpty(message = "Field must not be empty")
    private List<Integer> teachers;
}
