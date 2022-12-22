package infinity.learningcenter.repository;

import infinity.learningcenter.dao.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query("select t from Teacher t where t.booked = ?1")
    List<Teacher> getTeachersByStatus(Boolean status);
    Optional<Teacher> findFirstByName(String name);
}