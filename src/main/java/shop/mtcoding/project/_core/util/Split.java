package shop.mtcoding.project._core.util;

import java.util.ArrayList;
import java.util.List;

public class Split {

    // 년/월/일 -> 년으로 포맷
    public static String YearDateSplit(String date) {
        List<String> dateList = new ArrayList<>();
        String[] splitDate = date.split("-");
        String year = splitDate[0];
        return year;
    }
}
