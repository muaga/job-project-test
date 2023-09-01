package shop.mtcoding.project.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

@DataJpaTest
public class CommunityControllerTest {

    @Autowired
    private CommunityService communityService;

    // 검색 후 게시물 목록 가져오기
    @Test
    public void 게시물목록_test() {
        String keyword = "6";
        Integer page = 1;
        Page<Community> communityPG = communityService.검색후게시물목록보기(page, keyword);
        System.out.println("테스트 : " + communityPG.getSize());
    }

}