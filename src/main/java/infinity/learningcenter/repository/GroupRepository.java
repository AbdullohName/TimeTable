package infinity.learningcenter.repository;

import infinity.learningcenter.dao.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findFirstByName(String name);
    @Query("select g from Group g where g.weekId = ?1")
    List<Group> getGroupByWeekIdd(Integer id);
}