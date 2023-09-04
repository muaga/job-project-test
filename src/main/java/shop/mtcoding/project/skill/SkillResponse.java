package shop.mtcoding.project.skill;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class SkillResponse {

    @Getter
    @Setter
    public static class SkillNameDTO {
        private String skillName;

        @Builder
        public SkillNameDTO(String skillName) {
            this.skillName = skillName;
        }
    }
}
