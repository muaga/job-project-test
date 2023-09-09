package shop.mtcoding.project.resume;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ResumeServiceTest {

        @Autowired
        private ResumeRepository resumeRepository;

        @Test
        public void searchResume_test() {
                List<Resume> resumeList = resumeRepository.findByUserId(1);
                System.out.println("테스트 : " + resumeList.size());
                System.out.println("테스트 : " + resumeList.get(0).getTitle());

        }

}
