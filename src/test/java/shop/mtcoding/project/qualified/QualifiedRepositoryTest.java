package shop.mtcoding.project.qualified;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class QualifiedRepositoryTest {

    @Autowired
    private QualifiedRepository qualifiedRepository;

    @Test
    public void mfindByJobOpeningId_test() {
        List<Qualified> qualified = qualifiedRepository.mfindByJobOpeningId(1);
        for (Qualified qualifiedContent : qualified) {
            System.out.println("테스트 : " + qualifiedContent.getQualifiedContent());
        }
    }
}
