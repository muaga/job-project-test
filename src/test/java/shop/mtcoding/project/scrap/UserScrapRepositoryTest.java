package shop.mtcoding.project.scrap;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningMainDTO;
import shop.mtcoding.project.scrap.UserScrapResponse.ScrapJobOpeningDTO;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.Skill;
import shop.mtcoding.project.skill.SkillResponse.SkillNameDTO;
import shop.mtcoding.project.user.User;

@DataJpaTest
public class UserScrapRepositoryTest {

    @Autowired
    private UserScrapRepository userScrapRepository;

    @Test
    public void save_test() {
        UserScrap userScrap = UserScrap.builder()
                .jobOpening(JobOpening.builder().id(1).build())
                .user(User.builder().id(1).build())
                .build();

        userScrapRepository.save(userScrap);
        System.out.println("save 완료");

    }

    @Test
    public void mfindByJobOpeningId_test() {
        UserScrap userScrap = userScrapRepository.mfindByJobOpeningId(1, 1).get();
        System.out.println("테스트 : " + userScrap.getUser().getUserName());
    }

    @Test
    public void findByUserIdFromResume_test() {
        List<UserScrap> userScrapList = userScrapRepository.findByUserIdFromUser(1);
        System.out.println("테" + userScrapList.get(0).getJobOpening().getTitle());

        List<ScrapJobOpeningDTO> scrapJobOpeningDTOList = new ArrayList<>();
        for (UserScrap userScrap : userScrapList) {

            List<String> skillList = new ArrayList<>();
            for (RequiredSkill skill : userScrap.getJobOpening().getRequiredSkillList()) {
                String skillName = skill.getSkill().getSkill();
                skillList.add(skillName);
            }

            // 이중 for문을 방지하기 위해, 배열을 하나의 문자열로 만들기
            String skillListString = String.join("  |  ", skillList);

            ScrapJobOpeningDTO scrapJobOpeningDTO = ScrapJobOpeningDTO.builder()
                    .jobOpeningId(userScrap.getJobOpening().getId())
                    .title(userScrap.getJobOpening().getTitle())
                    .compName(userScrap.getUser().getUserName())
                    .edu(userScrap.getJobOpening().getEdu())
                    .skillName(skillListString)
                    .build();

            scrapJobOpeningDTOList.add(scrapJobOpeningDTO);
        }

        for (ScrapJobOpeningDTO scrapJobOpeningDTO : scrapJobOpeningDTOList) {
            System.out.println("테테테" + scrapJobOpeningDTO.getTitle());
            System.out.println("테테테 : " + scrapJobOpeningDTO.getSkillName());
        }
    }
}
