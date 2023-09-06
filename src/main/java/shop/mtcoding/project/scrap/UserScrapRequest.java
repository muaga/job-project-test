package shop.mtcoding.project.scrap;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class UserScrapRequest {

    @Getter
    @Setter
    public static class UserScrapDTO {
        private Integer jobOpeningId;
    }

    @Getter
    @Setter
    public static class UserScrapDeleteDTO {
        private Integer jobOpeningId;

    }
}
