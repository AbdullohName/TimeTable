package infinity.learningcenter.repository;

import infinity.learningcenter.dao.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTypeRepository extends JpaRepository<CourseType, Integer> {
}