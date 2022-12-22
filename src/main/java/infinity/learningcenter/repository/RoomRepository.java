package infinity.learningcenter.repository;

import infinity.learningcenter.dao.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findFirstByName(String name);
//    List<Room> findAllByIsEmpty(Boolean status);
    @Query("select r from Room r where r.booked = ?1")
    List<Room> getAllByStatus(Boolean status);
}