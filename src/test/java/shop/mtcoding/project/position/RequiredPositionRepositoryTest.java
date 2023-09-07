package shop.mtcoding.project.position;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.project.skill.RequiredSkill;

@DataJpaTest
public class RequiredPositionRepositoryTest {

    @Autowired
    private RequiredPositionRepository requiredPositionRepository;

    @Autowired
    private PositionRepository positionRepository;

    public void mfindByPositionId_test() {
        List<RequiredPosition> rp = requiredPositionRepository.mfindByPositionId(1);
        System.out.println("테스트 : " + rp.size());
        System.out.println("테스트 : " + rp.get(0).getJobOpening().getTitle());
    }

    @Test
    public void findAll_test() {
        List<Position> positionList = positionRepository.findAll();
        for (Position positionName : positionList) {
            System.out.println("테스트 : " + positionName.getPosition());
        }
    }

    // @Test
    // public void mfindByIdJoinPosition() {

    // List<RequiredPosition> requiredPosition =
    // requiredPositionRepository.mfindByIdJoinPosition(1);
    // List<PositionNameDTO> positionNameDTOList = new ArrayList<>();
    // for (RequiredPosition rpList : requiredPosition) {
    // PositionNameDTO dtos = PositionNameDTO.builder()
    // .positionName(rpList.getPosition().getPosition())
    // .build();
    // positionNameDTOList.add(dtos);
    // }

    // System.out.println("테스트 : " + positionNameDTOList.get(0).getPositionName());

    // }
}
