package shop.mtcoding.project.jobopening;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import shop.mtcoding.project.resume.ResumeRepository;
import shop.mtcoding.project.user.User;
import shop.mtcoding.project.user.UserRepository;

public class JobOpeningServiceTest {

    @Autowired
    private UserRepository userRepository;

    // @Test
    // public void careerYearChange_test() {

    // JobOpening jobOpening = JobOpening.builder()
    // .careerYear(null)
    // .build();

    // System.out.println("테스트 : " + jobOpening.getCareerYear());

    // // careerYear - "null" 또는 "" -> ""
    // String careerYear = jobOpening.getCareerYear();
    // if (careerYear == null || careerYear.isEmpty() ||
    // "null".equalsIgnoreCase(careerYear)) {
    // jobOpening.setCareerYear(" ");
    // }

    // System.out.println("테스트 : " + jobOpening.getCareerYear());
    // }

    @Test
    public void findById_test() {

        // List<Resume> resumeList = resumeRepository.mfindByUserId(1);

        User user = userRepository.findById(1).get();

        // try {
        // System.out.println("테스트 : " + resumeList.get(0).getTitle());
        // } catch (Exception e) {
        // System.out.println("비어있습니다.");
        // }

        // // 부가로직 - 이력서 null
        // if (resumeList.isEmpty()) {
        // throw new MyApiException("이력서를 먼저 작성해주세요.");
        // }

        // // 유저의 이력서 갯수
        // Integer sumResume = resumeList.size();

        // // 날짜포맷한 resume 담은 DTO 생성
        // List<ApplyResumeInJobOpeningDTO> formatResume = new ArrayList<>();

        // for (Resume resume : resumeList) {
        // // 날짜포맷
        // Timestamp resumeCreatedAt = resume.getCreatedAt();
        // String resumeCreatedAtFormat = FormatDate.formatDate(resumeCreatedAt);

        // ApplyResumeInJobOpeningDTO dtos = ApplyResumeInJobOpeningDTO.builder()
        // .resumeId(resume.getId())
        // .userTel(resume.getTel())
        // .resumeTitle(resume.getTitle())
        // .createdAtFormat(resumeCreatedAtFormat)
        // .build();

        // formatResume.add(dtos);
        // }

        // // view를 위한 DTO 생성
        // ResumeInJobOpeningDTO resumeInJobOpeningDTO = ResumeInJobOpeningDTO.builder()
        // .userEmail("nnnnn@nnnn.com")
        // .applyResumeInJobOpeningDTO(formatResume)
        // .totalResume(sumResume)
        // .build();

        // System.out.println("테스트 : " + resumeInJobOpeningDTO.getUserEmail());

    }

}
