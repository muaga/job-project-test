package shop.mtcoding.project.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    // comp_ 커뮤니티 댓글 작성
    @PostMapping("comp/community/reply/save")
    public String compReplySave(ReplyRequest.ReplySaveDTO replySaveDTO) {
        replyService.댓글작성(replySaveDTO);
        return "redirect:/comp/community/board/" + replySaveDTO.getBoardId();
    }

}
