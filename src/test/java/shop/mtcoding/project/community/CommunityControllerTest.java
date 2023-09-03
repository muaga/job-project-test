package shop.mtcoding.project.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.project.community.CommunityResponse.BoardDetailDTO;

@DataJpaTest
public class CommunityControllerTest {

    @Autowired
    private CommunityService communityService;

    // 검색 후 게시물 목록 가져오기
    // @Test
    // public void 게시물목록_test() {
    // String keyword = "6";
    // Integer page = 1;
    // Page<BoardDetailDTO> boardDetailDTO = communityService.검색후게시물목록(1, keyword);
    // System.out.println("테스트 : " + boardDetailDTO.getSize());
    // }

    @Test
    public void compBoardDetail_test() {
        BoardDetailDTO boardDetailDTO = communityService.상세게시물(2);
        System.out.println("테스트 : " + boardDetailDTO);
    }

}
