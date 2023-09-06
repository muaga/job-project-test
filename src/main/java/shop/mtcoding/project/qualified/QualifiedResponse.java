package shop.mtcoding.project.qualified;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class QualifiedResponse {

    @Getter
    @Setter
    public static class QualifiedContentDTO {

        private String qualifiedContent;

        @Builder
        public QualifiedContentDTO(String qualifiedContent) {
            this.qualifiedContent = qualifiedContent;
        }
    }

}
