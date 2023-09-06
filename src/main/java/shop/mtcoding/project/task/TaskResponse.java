package shop.mtcoding.project.task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class TaskResponse {

    @Getter
    @Setter
    public static class TaskContentDTO {
        private String taskContent;

        public TaskContentDTO() {
        }

        @Builder
        public TaskContentDTO(String taskContent) {
            this.taskContent = taskContent;
        }

    }
}
