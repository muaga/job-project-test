package shop.mtcoding.project.jobopening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningDetailDTO;
import shop.mtcoding.project.scrap.UserScrapRequest;

@Controller
public class JobOpeningController {

    @Autowired
    private JobOpeningService jobOpeningService;

    // comp_ 채용공고 상세 화면
    @GetMapping("comp/jobopening/{id}")
    public String compJobOpeningDetail(@PathVariable Integer id, Model model) {
        JobOpeningDetailDTO jobOpeningDetailDTO = jobOpeningService.상세채용공고(id);
        model.addAttribute("jobOpeningDetailDTO", jobOpeningDetailDTO);
        return "comp/comp_job_opening_detail";
    }

    // user_채용공고 상세 화면
    @GetMapping("user/jobopening/{id}")
    public String userJobOpeningDetail(@PathVariable Integer id, Model model) {
        JobOpeningDetailDTO jobOpeningDetailDTO = jobOpeningService.상세채용공고(id);
        model.addAttribute("jobOpeningDetailDTO", jobOpeningDetailDTO);
        return "user/user_job_opening_apply";
    }

}
