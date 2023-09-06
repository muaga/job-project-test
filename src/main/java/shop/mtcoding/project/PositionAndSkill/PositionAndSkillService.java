package shop.mtcoding.project.PositionAndSkill;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project.PositionAndSkill.PositionAndSkillResponse.PositionAndSkillNameDTO;

@Service
public class PositionAndSkillService {

    @Autowired
    private PositionAndSkillRepository positionAndSkillRepository;

    public List<PositionAndSkillNameDTO> 포지션과스킬(Integer positionId) {
        List<PositionAndSkill> positionAndSkillList = positionAndSkillRepository.mfindByPositionId(positionId);

        List<PositionAndSkillNameDTO> skillNameList = new ArrayList<>();
        for (PositionAndSkill skillName : positionAndSkillList) {
            PositionAndSkillNameDTO positionAndSkillNameDTOList = PositionAndSkillNameDTO.builder()
                    .skillName(skillName.getSkill().getSkill())
                    .build();

            skillNameList.add(positionAndSkillNameDTOList);
        }
        return skillNameList;
    }
}
