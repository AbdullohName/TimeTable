package infinity.learningcenter.repository;

import infinity.learningcenter.dao.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findFirstByName(String name);

}