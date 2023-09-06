package shop.mtcoding.project.apply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    // user_ 공고 지원
    @PostMapping("/user/apply")
    public String userApply(ApplyRequest.ApplySaveDTO applySaveDTO, Model model) {
        applyService.지원(applySaveDTO);
        Integer id = applySaveDTO.getSelectJobOpeningId();
        return "redirect:/";
    }
}