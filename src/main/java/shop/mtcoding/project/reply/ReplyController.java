package shop.mtcoding.project.reply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import shop.mtcoding.project._core.error.ex.MyApiException;
import shop.mtcoding.project._core.util.ApiUtil;
import shop.mtcoding.project.user.User;

@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private HttpSession session;

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

    // 자바스크립트로 댓글등록
    @PostMapping("/api/community/reply/save")
    public @ResponseBody ApiUtil<String> save(@RequestBody ReplyRequest.ReplySaveDTO replySaveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyApiException("로그인 후 작성이 가능합니다.");
        }
        replyService.댓글작성(1, replySaveDTO);
        return new ApiUtil<String>(true, "댓글쓰기 성공");
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
