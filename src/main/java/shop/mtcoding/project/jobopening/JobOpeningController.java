package shop.mtcoding.project.jobopening;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    // comp_ 채용공고 메인 화면
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

    // comp_ 채용정보 화면

    @GetMapping("/comp/jobOpening/select")
    public String compJobOpeningSelectForm(Model model) {
        List<Position> positionList = positionService.포지션이름();
        List<Skill> skillList = skillService.스킬이름();
        model.addAttribute("positionList", positionList);
        model.addAttribute("skillList", skillList);
        model.addAttribute("positionList", positionList);
        return "comp/comp_emp_info";
    }

    @GetMapping("/api/jobOpening/select/position")
    public @ResponseBody List<JobOpeningMainDTO> jobOpeningSelectPosition(@RequestParam Integer positionId) {
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.포지션별채용정보(positionId);
        return jobOpeningMainDTO;
    }

    @GetMapping("/api/jobOpening/select/skill")
    public @ResponseBody List<JobOpeningMainDTO> jobOpeningSelectSkill(@RequestParam Integer skillId) {
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.스킬별채용정보(skillId);
        return jobOpeningMainDTO;
    }

    @GetMapping("/api/jobOpening/select/all")
    public @ResponseBody List<JobOpeningMainDTO> jobOpeningSelectAll() {
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.메인화면();
        return jobOpeningMainDTO;
    }

}
