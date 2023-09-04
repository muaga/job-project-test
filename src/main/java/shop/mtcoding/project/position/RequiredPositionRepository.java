package shop.mtcoding.project.position;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequiredPositionRepository extends JpaRepository<RequiredPosition, Integer> {

    @Query("select r from RequiredPosition as r left join fetch r.position as rp left join r.jobOpening as rj where rj.id = :id")
    List<RequiredPosition> mfindByIdJoinPosition(@Param("id") Integer id);

}
