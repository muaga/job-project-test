package shop.mtcoding.project.skill;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.skill.SkillResponse.SkillNameDTO;

@DataJpaTest
public class RequiredSkillRepositoryTest {

    @Autowired
    private RequiredSkillRepository requiredSkillRepository;

    @Test
    public void mfindByIdJoinSkill_test() {

        List<RequiredSkill> requiredSkill = requiredSkillRepository.mfindByIdJoinSkill(1);
        List<SkillNameDTO> skillNameDTOList = new ArrayList<>();
        for (RequiredSkill rsList : requiredSkill) {
            SkillNameDTO dtos = SkillNameDTO.builder()
                    .skillName(rsList.getSkill().getSkill())
                    .build();
            skillNameDTOList.add(dtos);
        }

        System.out.println("테스트 : " + skillNameDTOList.get(0).getSkillName());

    }
}
