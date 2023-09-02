package shop.mtcoding.project.reply;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project.reply.ReplyRequest.ReplySaveDTO;
import shop.mtcoding.project.user.User;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private HttpSession session;

    @Transactional
    public void 댓글작성(ReplySaveDTO replySaveDTO) {
        // 로그인 인증
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 공백 또는 null 방지
        if (replySaveDTO.getComment() == null || replySaveDTO.getComment().isEmpty()) {
            throw new MyException("내용을 전부 입력해주세요");
        }

        Reply reply = Reply.builder()
                .comment(replySaveDTO.getComment())
                .user(User.builder().id(sessionUser.getId()).build())
                .build();
        replyRepository.save(reply);
    }

}
