package shop.mtcoding.project.util;

import org.junit.jupiter.api.Test;

import shop.mtcoding.project._core.util.Split;

public class splitTest {

    @Test
    public void split_test() {
        String date = "2023-05-15";
        String year = Split.YearDateSplit(date);
        System.out.println("테스트 : " + year);
    }

}
