package shop.mtcoding.project.PositionAndSkill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PositionAndSkillRepository extends JpaRepository<PositionAndSkill, Integer> {

    @Query("select p from PositionAndSkill as p where p.position.id = :id ")
    List<PositionAndSkill> mfindByPositionId(@Param("id") Integer PositionId);
}
