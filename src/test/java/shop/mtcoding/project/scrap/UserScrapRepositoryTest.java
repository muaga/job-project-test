package shop.mtcoding.project.scrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.project.jobopening.JobOpening;
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

}
