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

        // 경력 또는 지역 조회
        @Query("SELECT j FROM JobOpening j WHERE j.career like %:career% or j.compAddress like %:compAddress%")
        List<JobOpening> mFindBySelectedCareerOrLocation(@Param("career") String career,
                        @Param("compAddress") String location);

        // 경력과 지역 조회
        @Query("SELECT j FROM JobOpening j WHERE j.career like %:career% and j.compAddress like %:compAddress%")
        List<JobOpening> mFindBySelectedCareerAndLocation(@Param("career") String career,
                        @Param("compAddress") String location);

}
