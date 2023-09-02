package shop.mtcoding.project.community;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.project.reply.Reply;

@DataJpaTest
public class CommunityRepositoryTest {

    @Autowired
    private CommunityRepository communityRepository;

    // update test
    @Test
    public void update_test() {
        Community communityBoard1 = communityRepository.findById(6).get();
        System.out.println("테스트 : " + communityBoard1.getTitle());
        communityBoard1.setTitle("우히히");

        Community communityBoard2 = communityRepository.findById(1).get();
        System.out.println("테스트 : " + communityBoard2.getTitle());
        communityBoard2.setTitle("헤헤헤");

        System.out.println("테스트 : ==========================================");

        // community를 다시 조회하여, 제목이 변경되었는 지 확인하기
        List<Community> communityList = communityRepository.findAll();
        for (Community community2 : communityList) {
            System.out.println("테스트 : " + community2.getTitle());
        }
    }

    // mfindByIdJoinReplyAndBoard test
    @Test
    public void mfindByIdJoinReplyAndBoard_test() {
        Community community = communityRepository.mfindByIdJoinReplyAndBoard(1).get();
        System.out.println("테스트 : " + community.getTitle());
        System.out.println("테스트 : " + community.getContent());
        System.out.println("테스트 : " + community.getUser().getUserName());

        // join쿼리로, user와 reply, community에 대한 정보를 쿼리 실행 1번으로 가능해졌다.
        for (Reply reply : community.getReplyList()) {
            System.out.println("테스트 : " + reply.getComment());
            System.out.println("테스트 : " + reply.getUser().getUserName());
        }
    }

    // save test
    @Test
    public void save_test() {
        Community community = Community.builder()
                .title("안녕하세요")
                .content("그동안 감사했습니다.")
                .build();
        Community saveCommunity = communityRepository.save(community);

        System.out.println("테스트 : " + saveCommunity.getTitle());
        System.out.println("테스트 : " + saveCommunity.getContent());
    }

    // @Test
    // public void mfindByAllJoinUser_test() {
    // List<Community> communityList = communityRepository.mfindByAllJoinUser();
    // System.out.println("테스트 : " + communityList.get(0).getTitle());
    // System.out.println("테스트 : " + communityList.get(0).getContent());
    // System.out.println("테스트 : " + communityList.get(0).getUser().getUserName());
    // System.out.println("테스트 : " +
    // communityList.get(0).getUser().getUserEmailId());

    // for (Community community : communityList) {
    // System.out.println("테스트 : " + community);
    // System.out.println("테스트 : " + community.getContent());
    // System.out.println("테스트 : " + community.getUser().getUserName());
    // System.out.println("테스트 : " + community.getUser().getUserEmailId());
    // }
    // }

    // findAll test
    @Test
    public void findAll_test() {
        List<Community> communityList = communityRepository.findAll();
        // System.out.println("테스트 : " + communityList.get(0).getTitle());
        // System.out.println("테스트 : " + communityList.get(0).getContent());
        // System.out.println("테스트 : " + communityList.get(0).getUser().getUserName());
        // System.out.println("테스트 : " +
        // communityList.get(0).getUser().getUserEmailId());
        for (Community community : communityList) {
            System.out.println("테스트 : " + community);
            System.out.println("테스트 : " + community.getContent());
            System.out.println("테스트 : " + community.getUser().getUserName());
            System.out.println("테스트 : " + community.getUser().getUserEmailId());
        }
    }

}
