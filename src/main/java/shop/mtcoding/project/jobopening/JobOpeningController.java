package shop.mtcoding.project.jobopening;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project.PositionAndSkill.PositionAndSkillService;
import shop.mtcoding.project.PositionAndSkill.PositionAndSkillResponse.PositionAndSkillNameDTO;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningDetailDTO;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningMainDTO;
import shop.mtcoding.project.position.Position;
import shop.mtcoding.project.position.PositionService;
import shop.mtcoding.project.resume.ResumeService;
import shop.mtcoding.project.resume.ResumeResponse.ResumeInJobOpeningDTO;
import shop.mtcoding.project.skill.Skill;
import shop.mtcoding.project.skill.SkillService;

@Controller
public class JobOpeningController {

    @Autowired
    private JobOpeningService jobOpeningService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private PositionAndSkillService positionAndSkillService;

    // comp_ 채용공고 메인 화면
    @GetMapping("/comp/mainForm")
    public String compMainForm(Model model) {
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.메인화면();
        model.addAttribute("jobOpeningMainDTO", jobOpeningMainDTO);
        return "comp_index";
    }

    // user_ 채용공고 메인 화면
    @GetMapping("/user/mainForm")
    public String userMainForm(Model model) {
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.메인화면();
        model.addAttribute("jobOpeningMainDTO", jobOpeningMainDTO);
        return "user_index";
    }

    // comp_ 채용공고 상세 화면
    @GetMapping("/comp/jobOpening/{id}")
    public String compJobOpeningDetailForm(@PathVariable Integer id, Model model) {
        JobOpeningDetailDTO jobOpeningDetailDTO = jobOpeningService.상세채용공고(id);
        ResumeInJobOpeningDTO resumeInJobOpeningDTO = resumeService.지원화면();
        model.addAttribute("jobOpeningDetailDTO", jobOpeningDetailDTO);
        model.addAttribute("resumeInJobOpeningDTO", resumeInJobOpeningDTO);
        return "comp/comp_job_opening_detail";
    }

    // user_채용공고 상세 화면
    @GetMapping("/user/jobOpening/{id}")
    public String userJobOpeningDetailForm(@PathVariable Integer id, Model model) {
        JobOpeningDetailDTO jobOpeningDetailDTO = jobOpeningService.상세채용공고(id);
        ResumeInJobOpeningDTO resumeInJobOpeningDTO = resumeService.지원화면();
        model.addAttribute("jobOpeningDetailDTO", jobOpeningDetailDTO);
        model.addAttribute("resumeInJobOpeningDTO", resumeInJobOpeningDTO);
        return "user/user_job_opening_apply";
    }

    // user_ 채용정보 화면
    @GetMapping("/comp/jobOpening/select")
    public String compJobOpeningSelectForm(Model model) {
        List<Position> positionList = positionService.포지션이름();
        List<Skill> skillList = skillService.스킬이름();
        model.addAttribute("positionList", positionList);
        model.addAttribute("skillList", skillList);
        model.addAttribute("positionList", positionList);
        return "comp/comp_emp_info";
    }

    // 채용정보 제일 첫 화면
    @GetMapping("/api/jobOpening/select/all")
    public @ResponseBody List<JobOpeningMainDTO> jobOpeningSelectAll() {
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.메인화면();
        return jobOpeningMainDTO;
    }

    // 경력or지역/경력and지역을 기반으로 데이터 필터링
    @GetMapping("/api/jobOpening/select/cl")
    public @ResponseBody List<JobOpeningMainDTO> jobOpeningSelectByCareerOrLocation(
            @RequestParam(name = "career", required = false) String career,
            @RequestParam(name = "careerYear", required = false) String careerYear,
            @RequestParam(name = "location", required = false) String location) {
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.경력과지역선택(career, careerYear, location);
        return jobOpeningMainDTO;
    }

    // 포지션or스킬/포지션ans스킬을 기반으로 데이터 필터링
    @GetMapping("/api/jobOpening/select/pk")
    public @ResponseBody List<JobOpeningMainDTO> jobOpeningSelectByPositionOrSkill(
            @RequestParam(required = false) List<Integer> positionIdList,
            @RequestParam(required = false) List<Integer> skillIdList) {
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.포지션과스킬선택(positionIdList, skillIdList);
        return jobOpeningMainDTO;
    }

    @GetMapping("/test/haha")
    public @ResponseBody String tt(Integer number) {
        System.out.println(number);
        return "tt : " + number;
    }

    // // 스킬을 기반으로 데이터 필터링
    // @GetMapping("/api/jobOpening/select/skill")
    // public @ResponseBody List<JobOpeningMainDTO>
    // jobOpeningSelectSkill(@RequestParam Integer skillId) {
    // List<JobOpeningMainDTO> jobOpeningMainDTO =
    // jobOpeningService.스킬별채용정보(skillId);
    // return jobOpeningMainDTO;
    // }

}
