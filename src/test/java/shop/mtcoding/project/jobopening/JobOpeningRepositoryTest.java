package shop.mtcoding.project.jobopening;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class JobOpeningRepositoryTest {

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    // mfindByIdJoinJobOpeningAndUser test
    @Test
    public void mfindByIdJoinJobOpeningAndUser_test() {
        JobOpening jobOpening = jobOpeningRepository.mfindByIdJoinJobOpeningAndUser(1).get();

        System.out.println("테스트 : " + jobOpening.getTitle());
        System.out.println("테스트 : " + jobOpening.getUser().getUserName());
    }
}
