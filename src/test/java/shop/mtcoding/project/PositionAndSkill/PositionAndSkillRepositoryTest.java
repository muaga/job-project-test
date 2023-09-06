package shop.mtcoding.project.PositionAndSkill;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PositionAndSkillRepositoryTest {

    @Autowired
    private PositionAndSkillRepository positionAndSkillRepository;

    @Test
    public void mfindByPositionId() {
        List<PositionAndSkill> positionAndSkillList = positionAndSkillRepository.mfindByPositionId(1);
        for (PositionAndSkill skill : positionAndSkillList) {
            System.out.println("테스트 : " + skill.getSkill().getSkill());
        }
    }
}
