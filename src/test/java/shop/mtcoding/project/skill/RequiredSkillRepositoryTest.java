package shop.mtcoding.project.skill;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RequiredSkillRepositoryTest {

    @Autowired
    private RequiredSkillRepository requiredSkillRepository;

    @Test
    public void mfindByAllJoinSkillAndJobOpening_test() {
        List<RequiredSkill> rs = requiredSkillRepository.mfindByAllJoinSkillAndJobOpening();
        for (RequiredSkill requiredSkill : rs) {
            System.out.println("테스트 : " + requiredSkill.getJobOpening().getId());
            System.out.println("테스트 : " + requiredSkill.getJobOpening().getUser().getUserName());
            System.out.println("테스트 : " + requiredSkill.getJobOpening().getCareer());
            System.out.println("테스트 : " + requiredSkill.getJobOpening().getCareerYear());
            System.out.println("테스트 : " + requiredSkill.getSkill().getSkill());
        }
    }

    @Test
    public void mfindByIdJoinSkillAndJobOpening_test() {
        List<RequiredSkill> rs = requiredSkillRepository.mfindByIdJoinSkillAndJobOpening(1);
        for (RequiredSkill requiredSkill : rs) {
            // System.out.println("테스트 : " + requiredSkill.getJobOpening().getTitle());
            // System.out.println("테스트 : " +
            // requiredSkill.getJobOpening().getUser().getUserName());
            // System.out.println("테스트 : " + requiredSkill.getSkill().getSkill());
        }

        System.out.println("테스트 : " + rs.get(0).getJobOpening().getTitle());
        System.out.println("테스트 : " + rs.get(0).getJobOpening().getCompAddress());
        System.out.println("테스트 : " + rs.get(0).getJobOpening().getCareer());

    }
}
