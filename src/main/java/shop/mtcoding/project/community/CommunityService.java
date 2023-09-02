package shop.mtcoding.project.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project.user.User;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private HttpSession session;

    public Page<Community> 게시물목록(Integer page) {
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        return communityRepository.findAll(pageable);
    }

    public Page<Community> 검색후게시물목록(Integer page, String keyword) {
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        return communityRepository.mfindBySearchAll(pageable, keyword);
    }

    @Transactional
    public void 게시물작성(Integer sessionId, CommunityRequest.BoardSaveDTO boardSaveDTO) {
        // 공백 또는 null 방지
        if (boardSaveDTO.getTitle() == null || boardSaveDTO.getTitle().isEmpty()) {
            throw new MyException("내용을 전부 입력해주세요");
        }
        if (boardSaveDTO.getContent() == null || boardSaveDTO.getContent().isEmpty()) {
            throw new MyException("내용을 전부 입력해주세요");
        }

        Community community = Community.builder()
                .title(boardSaveDTO.getTitle())
                .content(boardSaveDTO.getContent())
                .user(User.builder().id(sessionId).build())
                .build();
        communityRepository.save(community);
    }

    public Community 상세게시물(Integer id) {
        Optional<Community> communityOP = communityRepository.mfindByIdJoinReplyAndBoard(id);
        if (communityOP.isPresent()) {
            return communityOP.get();
        } else {
            throw new MyException("해당 게시글을 찾을 수 없습니다.");
        }
    }

    public Community 게시물내용(Integer id) {
        Optional<Community> communityOP = communityRepository.findById(id);
        if (communityOP.isPresent()) {
            return communityOP.get();
        } else {
            throw new MyException("해당 게시글을 찾을 수 없습니다.");
        }
    }

    @Transactional
    public void 게시물수정(Integer id, CommunityRequest.BoardUpdateDTO boardUpdateDTO) {
        // 로그인 인증
        // User sessionUser = (User) session.getAttribute("sessionUser");

        Optional<Community> communityOP = communityRepository.findById(id);
        if (communityOP.isPresent()) {
            Community community = communityOP.get();
            System.out.println("테스트 : " + community.getTitle());

            // 권한 인증
            // if (sessionUser.getId() != community.getUser().getId()) {
            // throw new MyException("게시물 수정의 권한이 없습니다.");
            // }

            // 공백 또는 null 방지
            if (boardUpdateDTO.getTitle() == null || boardUpdateDTO.getTitle().isEmpty()) {
                throw new MyException("내용을 전부 입력해주세요");
            }
            if (boardUpdateDTO.getContent() == null || boardUpdateDTO.getContent().isEmpty()) {
                throw new MyException("내용을 전부 입력해주세요");
            }

            community.setTitle(boardUpdateDTO.getTitle());
            community.setContent(boardUpdateDTO.getContent());

        } else {
            throw new MyException("해당 게시글을 찾을 수 없습니다.");
        }
    }

    @Transactional
    public void 게시물삭제(Integer id) {
        // 로그인 인증
        // User sessionUser = (User) session.getAttribute("sessionUser");

        Optional<Community> communityOP = communityRepository.findById(id);
        if (communityOP.isPresent()) {

            // 권한 인증
            // if (sessionUser.getId() != community.getUser().getId()) {
            // throw new MyException("게시물 수정의 권한이 없습니다.");
            // }

            communityRepository.deleteById(id);
        } else {
            throw new MyException("해당 게시글을 찾을 수 없습니다.");
        }
    }

}
