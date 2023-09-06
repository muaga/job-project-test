package shop.mtcoding.project.skill;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequiredSkillRepository extends JpaRepository<RequiredSkill, Integer> {
    @Query("select r from RequiredSkill as r left join fetch r.skill as rs left join r.jobOpening as rj where rj.id = :id")
    List<RequiredSkill> mfindByIdJoinSkill(@Param("id") Integer id);
}
