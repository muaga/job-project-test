package shop.mtcoding.project.jobopening;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import shop.mtcoding.project._core.util.Split;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningMainDTO;
import shop.mtcoding.project.skill.RequiredSkill;

@Import(JobOpeningQueryRepository.class)
@DataJpaTest
public class JobOpeningRepositoryTest {

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private JobOpeningQueryRepository jobOpeningQueryRepository;

    @Test
    public void findByIdJoinPositionAndSkill_test() {
        List<Integer> positionIdList = new ArrayList<>();
        positionIdList.add(1);
        List<Integer> skillIdList = new ArrayList<>();
        skillIdList.add(1);
        List<JobOpeningMainDTO> dtos = jobOpeningQueryRepository.findByIdJoinPositionAndSkill(positionIdList,
                skillIdList);
        for (JobOpeningMainDTO jobOpeningMainDTO : dtos) {
            System.out.println("테스트 : " + jobOpeningMainDTO.getTitle());
        }
    }

    @Test
    public void mFfindBySelectedPositionOrSkill_test() {
        List<JobOpening> jobOpeningList = jobOpeningRepository.mFindBySelectedPositionAndSkill(1, null);
        for (JobOpening jobOpening : jobOpeningList) {
            System.out.println("테스트 : " + jobOpening.getTitle());
        }

        // and하면 하나가 null 값이면 아예 나오지 않는다.

        // List<JobOpening> jobOpeningList1 =
        // jobOpeningRepository.mFfindBySelectedPositionAndSkill(1, 1);
        // for (JobOpening jobOpening : jobOpeningList1) {
        // System.out.println("테스트 : " + jobOpening.getTitle());
        // }
    }

    @Test
    public void findBySelectedCareerOrCareerYear_test() {
        List<JobOpening> jobOpeningList = jobOpeningRepository.mFindBySelectedCareerAndLocation("경력 1년차", null);

        // jobOpening을 담기 위한 List
        List<JobOpeningMainDTO> jobOpeningMainDTOList = new ArrayList<>();
        for (JobOpening jobOpening : jobOpeningList) {

            // skillName을 담기 위한 List
            List<String> skillName = new ArrayList<>();
            for (RequiredSkill requiredSkill : jobOpening.getRequiredSkillList()) {
                String skill = requiredSkill.getSkill().getSkill();
                skillName.add(skill);
            }

            // 이중 for문을 방지하기 위해, 배열을 하나의 문자열로 만들기
            String skillListString = String.join(" · ", skillName);

            // 주소 포맷
            String Address = jobOpening.getCompAddress();
            String compAddressFormat = Split.AddressSplit(Address);

            JobOpeningMainDTO jobOpeningMainDTO = JobOpeningMainDTO.builder()
                    .jobOpeningId(jobOpening.getId())
                    .title(jobOpening.getTitle())
                    .compName(jobOpening.getUser().getUserName())
                    .compAddress(compAddressFormat)
                    .career(jobOpening.getCareer())
                    .careerYear(jobOpening.getCareerYear())
                    .skill(skillListString)
                    .build();
            jobOpeningMainDTOList.add(jobOpeningMainDTO);
        }

        for (JobOpeningMainDTO jobOpeningMainDTO : jobOpeningMainDTOList) {
            System.out.println("테스트 : " + jobOpeningMainDTO.getTitle());
        }

    }

    // mfindByIdJoinJobOpeningAndUser test
    @Test
    public void mfindByIdJoinJobOpeningAndUser_test() {
        JobOpening jobOpening = jobOpeningRepository.mfindByIdJoinJobOpeningAndUser(1).get();

        System.out.println("테스트 : " + jobOpening.getTitle());
        System.out.println("테스트 : " + jobOpening.getUser().getUserName());
    }

    @Test
    public void mfindByAllJoinJobOpeningAndUser_test() {
        List<JobOpening> jobOpeningList = jobOpeningRepository.mfindByAllJoinJobOpeningAndUser();

        System.out.println("테스트 : " + jobOpeningList.get(0).getRequiredSkillList().size());
        System.out.println("테스트 : " + jobOpeningList.get(0).getRequiredSkillList().get(0).getSkill().getSkill());
    }

}
