package shop.mtcoding.project.reply;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Query("select r from Reply r where r.user.id = :userId")
    List<Reply> findByUserId(@Param("userId") Integer userId);

}
