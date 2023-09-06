package shop.mtcoding.project.PositionAndSkill;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PositionAndSkillResponse {

    @Getter
    @Setter
    public static class PositionAndSkillNameDTO {
        private String skillName;

        @Builder
        public PositionAndSkillNameDTO(String skillName) {
            this.skillName = skillName;
        }
    }
}
