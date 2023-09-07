package shop.mtcoding.project.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import shop.mtcoding.project.user.User;

@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    // comp_ 커뮤니티 댓글 작성
    // @SessionAttribute User sessionUser
    @PostMapping("/comp/community/reply/save")
    public String compReplySave(ReplyRequest.ReplySaveDTO replySaveDTO) {
        replyService.댓글작성(1, replySaveDTO);
        return "redirect:/comp/community/board/" + replySaveDTO.getBoardId();
    }

    // user_ 커뮤니티 댓글 작성
    // @SessionAttribute User sessionUser
    @PostMapping("/user/community/reply/save")
    public String userReplySave(ReplyRequest.ReplySaveDTO replySaveDTO) {
        replyService.댓글작성(1, replySaveDTO);
        return "redirect:/user/community/board/" + replySaveDTO.getBoardId();
    }

    ////////////////////////////////////////////////////////////////////////

    // comp_ 커뮤니티 댓글 삭제
    // @SessionAttribute User sessionUser
    @PostMapping("/comp/community/reply/{id}/delete")
    public String compReplyDelete(@PathVariable Integer id) {
        replyService.댓글삭제(1, id);
        return "redirect:/comp/community/board/" + id;
    }

    // user_ 커뮤니티 댓글 삭제
    // @SessionAttribute User sessionUser
    @PostMapping("/user/community/reply/{id}/delete")
    public String userReplyDelete(@PathVariable Integer id) {
        replyService.댓글삭제(1, id);
        return "redirect:/user/community/board/" + id;
    }
}
