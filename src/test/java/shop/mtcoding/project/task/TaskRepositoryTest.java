package shop.mtcoding.project.task;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void mfindByJobOpeningId_test() {
        List<Task> task = taskRepository.mfindByJobOpeningId(1);
        for (Task taskContent : task) {
            System.out.println("테스트 : " + taskContent.getTaskContent());
        }
    }
}
