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

        // // 경력 또는 지역 조회
        // @Query("SELECT j FROM JobOpening j WHERE (j.career LIKE CONCAT('%', :career,
        // '%') OR j.career IS NULL OR :career IS NULL) OR (j.careerYear LIKE
        // CONCAT('%', :careerYear, '%') OR j.careerYear IS NULL OR :careerYear IS NULL)
        // OR (j.compAddress LIKE CONCAT('%', :compAddress, '%') OR j.compAddress IS
        // NULL OR :compAddress IS NULL)")
        // List<JobOpening> mFindBySelectedCareerOrCareerYearOrLocation(@Param("career")
        // String career,
        // @Param("careerYear") String careerYear,
        // @Param("compAddress") String location);

        // // 경력과 지역 조회
        // @Query("SELECT j FROM JobOpening j WHERE (j.career like
        // CONCAT('%',:career,'%') or j.careerYear like CONCAT('%',:careerYear,'%')) and
        // j.compAddress like CONCAT('%',:compAddress,'%')")
        // List<JobOpening>
        // mFindBySelectedCareerOrCareerYearAndLocation(@Param("career") String career,
        // @Param("careerYear") String careerYear,
        // @Param("compAddress") String location);

}
