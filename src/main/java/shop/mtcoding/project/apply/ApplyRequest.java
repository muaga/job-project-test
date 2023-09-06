package shop.mtcoding.project.apply;

import lombok.Getter;
import lombok.Setter;

public class ApplyRequest {

    // 공고 지원 DTO
    @Getter
    @Setter
    public static class ApplySaveDTO {
        private Integer selectResumeId;
        private Integer selectJobOpeningId;
    }

}
