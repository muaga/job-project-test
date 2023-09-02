package shop.mtcoding.project.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import shop.mtcoding.project.community.CommunityRequest.BoardSaveDTO;

@DataJpaTest
public class CommunityServiceTest {

    @Autowired
    private CommunityRepository communityRepository;

    // 게시물 작성하기
    @Test
    public void 게시물작성_test() {
        Community community = Community.builder()
                .title("안녕하세요")
                .content("그동안 감사했습니다.")
                .build();
        Community saveCommunity = communityRepository.save(community);

        System.out.println("테스트 : " + saveCommunity.getTitle());
        System.out.println("테스트 : " + saveCommunity.getContent());
    }

    // 검색 후 게시물 가져오기
    @Test
    public void 검색게시물목록_test() {
        Pageable pageable = PageRequest.of(0, 6, Sort.Direction.DESC, "id");
        Page<Community> communityPG = communityRepository.mfindBySearchAll(pageable, "제목");
        for (Community community : communityPG) {
            System.out.println("테스트 : " + community.getTitle());
        }
    }

    // 게시물 작성 유저네임 확인
    @Test
    public void 게시물유저네임_test() {
        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.DESC, "id");
        Page<Community> communityPG = communityRepository.findAll(pageable);
        System.out.println("테스트 : " + communityPG.getNumber());
        for (Community community : communityPG) {
            System.out.println("테스트 : " + community.getUser().getUserName());
        }
    }

    // 페이징 test
    @Test
    public void communityPage_test() {
        Pageable pageable = PageRequest.of(0, 4, Sort.Direction.DESC, "id");
        Page<Community> communityPG = communityRepository.findAll(pageable);

        System.out.println("테스트 : " + communityPG.getSize());
        System.out.println("테스트 : " + communityPG.getNumber());
        System.out.println("테스트 : " + pageable);
        System.out.println("테스트 :" + communityPG.getContent());

    }

}
