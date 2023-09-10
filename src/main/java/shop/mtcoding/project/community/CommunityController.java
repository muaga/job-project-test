package shop.mtcoding.project.community;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project._core.util.Script;
import shop.mtcoding.project.community.CommunityResponse.BoardDetailDTO;
import shop.mtcoding.project.community.CommunityResponse.BoardListDTO;
import shop.mtcoding.project.user.User;

@Controller
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private HttpSession session;

    // comp_ 커뮤니티 홈 화면
    @GetMapping("comp/community")
    public String compCommunity(@RequestParam(defaultValue = "0") Integer page, String keyword, Model model) {
        Page<BoardListDTO> boardListDTO = null;
        if (keyword == null || keyword.trim().isEmpty()) {
            boardListDTO = communityService.게시물목록(page);
        } else {
            boardListDTO = communityService.검색후게시물목록(page, keyword);
        }
        model.addAttribute("boardListDTO", boardListDTO);
        model.addAttribute("prev", boardListDTO.getNumber() - 1);
        model.addAttribute("next", boardListDTO.getNumber() + 1);
        return "comp/comp_community_list";
    }

    // user_ 커뮤니티 홈 화면
    @GetMapping("user/community")
    public String userCommunity(@RequestParam(defaultValue = "0") Integer page, String keyword, Model model) {
        Page<BoardListDTO> boardListDTO = null;
        if (keyword == null || keyword.trim().isEmpty()) {
            boardListDTO = communityService.게시물목록(page);
        } else {
            boardListDTO = communityService.검색후게시물목록(page, keyword);
        }
        model.addAttribute("boardListDTO", boardListDTO);
        model.addAttribute("prev", boardListDTO.getNumber() - 1);
        model.addAttribute("next", boardListDTO.getNumber() + 1);
        return "user/user_community_list";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    // comp_ 커뮤니티 글 등록 화면
    @GetMapping("/comp/community/board/saveForm")
    public String compBoardSaveForm() {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 작성이 가능합니다.");
        }
        return "comp/comp_community_write";
    }

    // user_ 커뮤니티 글 등록 화면
    @GetMapping("/user/community/board/saveForm")
    public String userBoardSaveForm() {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 작성이 가능합니다.");
        }
        return "user/user_community_write";
    }

    // comp_ 커뮤니티 글 작성
    @PostMapping("/comp/community/board/save")
    public String compBoardSave(CommunityRequest.BoardSaveDTO boardSaveDTO) {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 작성이 가능합니다.");
        }
        communityService.게시물작성(1, boardSaveDTO);
        return "redirect:/comp/community";
    }

    // user_ 커뮤니티 글 작성
    @PostMapping("/user/community/board/save")
    public String userBoardSave(CommunityRequest.BoardSaveDTO boardSaveDTO) {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 작성이 가능합니다.");
        }
        communityService.게시물작성(1, boardSaveDTO);
        return "redirect:/user/community";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    // comp_ 커뮤니티 글 상세보기 화면
    @GetMapping("/comp/community/board/{id}")
    public String compBoardDetailForm(@PathVariable Integer id, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardDetailDTO boardDetailDTO = communityService.상세게시물(id, sessionUser.getId());
        model.addAttribute("boardDetailDTO", boardDetailDTO);
        return "comp/comp_community_detail";
    }

    // user_ 커뮤니티 글 상세보기 화면
    @GetMapping("/user/community/board/{id}")
    public String userBoardDetailForm(@PathVariable Integer id, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardDetailDTO boardDetailDTO = communityService.상세게시물(id, sessionUser.getId());
        model.addAttribute("boardDetailDTO", boardDetailDTO);
        return "user/user_community_detail";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    // comp_ 커뮤니티 글 수정 화면
    @GetMapping("/comp/community/board/{id}/updateForm")
    public String compBoardUpdateForm(@PathVariable Integer id, Model model) {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 작성이 가능합니다.");
        }
        Community community = communityService.게시물내용(1, id);
        model.addAttribute("community", community);
        return "comp/comp_community_update";
    }

    // user_ 커뮤니티 글 수정 화면
    @GetMapping("/user/community/board/{id}/updateForm")
    public String userBoardUpdateForm(@PathVariable Integer id, Model model) {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 작성이 가능합니다.");
        }
        Community community = communityService.게시물내용(1, id);
        model.addAttribute("community", community);
        return "user/user_community_update";
    }

    // comp_ 커뮤니티 글 수정
    @PostMapping("/comp/community/board/{id}/update")
    public String compBoardUpdate(@PathVariable Integer id, CommunityRequest.BoardUpdateDTO boardUpdateDTO) {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 작성이 가능합니다.");
        }
        communityService.게시물수정(1, id, boardUpdateDTO);
        return "redirect:/comp/community/board/" + id;
    }

    // user_ 커뮤니티 글 수정
    @PostMapping("/user/community/board/{id}/update")
    public String userBoardUpdate(@PathVariable Integer id, CommunityRequest.BoardUpdateDTO boardUpdateDTO) {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 작성이 가능합니다.");
        }
        communityService.게시물수정(1, id, boardUpdateDTO);
        return "redirect:/user/community/board/" + id;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    // comp_ 커뮤니티 글 삭제
    @PostMapping("/comp/community/board/{id}/delete")
    public String compBoardDelete(@PathVariable Integer id) {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 작성이 가능합니다.");
        }
        communityService.게시물삭제(1, id);
        return "redirect:/comp/community";
    }

    // user_ 커뮤니티 글 삭제
    // @SessionAttribute User sessionUser
    @PostMapping("/user/community/board/{id}/delete")
    public String userBoardDelete(@PathVariable Integer id) {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 작성이 가능합니다.");
        }
        communityService.게시물삭제(1, id);
        return "redirect:/user/community";
    }
}