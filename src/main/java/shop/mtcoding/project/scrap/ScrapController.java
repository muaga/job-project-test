package shop.mtcoding.project.scrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.error.ex.MyApiException;
import shop.mtcoding.project._core.util.ApiUtil;
import shop.mtcoding.project.reply.ReplyRequest;

@Controller
public class ScrapController {

    @Autowired
    private ScrapService scrapService;

    // user_채용공고 스크랩
    @PostMapping("/api/user/jobopening/saveScrap")
    public @ResponseBody ApiUtil<String> userJobOpeningSaveScrap(
            @RequestBody UserScrapRequest.UserScrapDTO userScrapDTO) {
        scrapService.채용공고스크랩(1, userScrapDTO);
        return new ApiUtil<String>(true, "스크랩 성공");
    }

    // user_채용공고 스크랩 삭제
    @DeleteMapping("/api/user/jobopening/deleteScrap")
    public @ResponseBody ApiUtil<String> userJopOpeningScrapDelete(
            @RequestBody UserScrapRequest.UserScrapDeleteDTO userScrapDeleteDTO) {
        scrapService.채용공고스크랩삭제(1, userScrapDeleteDTO);
        return new ApiUtil<String>(true, "스크랩 삭제");
    }
}
