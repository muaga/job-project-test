package shop.mtcoding.project.jobopening;

import org.junit.jupiter.api.Test;

public class JobOpeningServiceTest {

    @Test
    public void careerYearChange_test() {

        JobOpening jobOpening = JobOpening.builder()
                .careerYear(null)
                .build();

        System.out.println("테스트 : " + jobOpening.getCareerYear());

        // careerYear - "null" 또는 "" -> ""
        String careerYear = jobOpening.getCareerYear();
        if (careerYear == null || careerYear.isEmpty() || "null".equalsIgnoreCase(careerYear)) {
            jobOpening.setCareerYear(" ");
        }

        System.out.println("테스트 : " + jobOpening.getCareerYear());
    }

}
