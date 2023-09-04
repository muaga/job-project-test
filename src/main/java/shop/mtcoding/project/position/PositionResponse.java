package shop.mtcoding.project.position;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PositionResponse {

    @Getter
    @Setter
    public static class PositionNameDTO {

        private String positionName;

        @Builder
        public PositionNameDTO(String positionName) {
            this.positionName = positionName;
        }
    }
}
