package shop.mtcoding.project.jobopening;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Integer> {

    // 공고 상세보기
    @Query("select j from JobOpening as j left join fetch j.user as ju where j.id = :id")
    Optional<JobOpening> mfindByIdJoinJobOpeningAndUser(@Param("id") Integer id);

    @Query("select j from JobOpening as j left join fetch j.user as ju")
    List<JobOpening> mfindByAllJoinJobOpeningAndUser();

    @Query("SELECT j FROM JobOpening j WHERE j.career like %:career% or j.comAddress like %:comAddress%")
    List<JobOpening> findBySelectedCareerOrLocation(@Param("career") String career,
            @Param("comAddress") String location);

    @Query("select distinct j from JobOpening j where j.compAddress like %:keyword%")
    List<JobOpening> mfindByCompAddress(@Param("keyword") String location);

}
