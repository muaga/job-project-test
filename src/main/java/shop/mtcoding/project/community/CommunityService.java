package shop.mtcoding.project.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import shop.mtcoding.project.community.CommunityRequest.BoardSaveDTO;
import shop.mtcoding.project.user.User;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    public Page<Community> 게시물목록(Integer page) {
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        // page는 상수가 아니기 때문에 받아야 한다.
        return communityRepository.findAll(pageable);
    }

    public Page<Community> 검색후게시물목록(Integer page, String keyword) {
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        return communityRepository.findBySearchAll(pageable, keyword);
    }

    public void 게시물작성(Integer sessionId, CommunityRequest.BoardSaveDTO boardSaveDTO) {
        Community community = Community.builder()
                .title(boardSaveDTO.getTitle())
                .content(boardSaveDTO.getContent())
                .user(User.builder().id(sessionId).build())
                .build();
        communityRepository.save(community);
    }
}
