package shop.mtcoding.project.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project._core.util.FormatDate;
import shop.mtcoding.project.community.Community;
import shop.mtcoding.project.community.CommunityRepository;
import shop.mtcoding.project.community.CommunityResponse.BoardDetailDTO;
import shop.mtcoding.project.reply.Reply;

@DataJpaTest
public class FormatTest {

    @Autowired
    private CommunityRepository communityRepository;

    @Test
    public void format_test() {
        Optional<Community> communityOP = communityRepository.mfindByIdJoinReplyAndBoard(1);
        if (communityOP.isPresent()) {
            Community community = communityOP.get();

            // 날짜 포맷
            Date boardCreatedAt = community.getCreatedAt();
            List<Reply> replyList = community.getReplyList();

            List<Date> replyCreatedAtList = new ArrayList<>();
            for (Reply reply : replyList) {
                Date replyCreatedAt = reply.getCreatedAt();
                replyCreatedAtList.add(replyCreatedAt);
                System.out.println("테스트 : " + replyCreatedAtList);
            }

            String boardFormatDate = FormatDate.formatDate(boardCreatedAt);
            List<String> replyFormatDateList = FormatDate.formatDateList(replyCreatedAtList);
            for (String replycreatedAt : replyFormatDateList) {
                System.out.println("테스트 - 포맷 : " + boardFormatDate);
            }

        } else {
            throw new MyException("해당 게시글을 찾을 수 없습니다.");
        }
    }

}
