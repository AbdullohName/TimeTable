package infinity.learningcenter.repository;

import infinity.learningcenter.dao.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findFirstByName(String name);
    @Query("select g from Group g where g.weekId = ?1")
    List<Group> getGroupByWeekIdd(Integer id);
    @Transactional
    @Modifying
    @Query("update Group g set g.roomId = null,g.teacherId = null,g.weekId = null where g.roomId > 0")
    void clearAll();
}