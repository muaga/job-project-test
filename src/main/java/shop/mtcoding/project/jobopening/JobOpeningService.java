package shop.mtcoding.project.jobopening;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyApiException;
import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project._core.util.FormatDate;
import shop.mtcoding.project._core.util.Split;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningDetailDTO;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningMainDTO;
import shop.mtcoding.project.position.RequiredPosition;
import shop.mtcoding.project.position.RequiredPositionRepository;
import shop.mtcoding.project.position.PositionResponse.PositionNameDTO;
import shop.mtcoding.project.qualified.Qualified;
import shop.mtcoding.project.qualified.QualifiedRepository;
import shop.mtcoding.project.qualified.QualifiedResponse.QualifiedContentDTO;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.RequiredSkillRepository;
import shop.mtcoding.project.skill.SkillResponse.SkillNameDTO;
import shop.mtcoding.project.task.Task;
import shop.mtcoding.project.task.TaskRepository;
import shop.mtcoding.project.task.TaskResponse.TaskContentDTO;

@Service
public class JobOpeningService {

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private RequiredSkillRepository requiredSkillRepository;

    @Autowired
    private RequiredPositionRepository requiredPositionRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private QualifiedRepository qualifiedRepository;

    public List<JobOpeningMainDTO> 채용정보(Integer positionId) {
        List<RequiredPosition> requiredPositionList = requiredPositionRepository.mfindByIdJoinPosition(positionId);

        List<JobOpeningMainDTO> jobOpeningMainDTOList = new ArrayList<>();
        for (RequiredPosition requiredPosition : requiredPositionList) {

            // skillName을 담기 위한 List
            List<String> skillName = new ArrayList<>();
            for (RequiredSkill requiredSkill : requiredPosition.getJobOpening().getRequiredSkillList()) {
                String skill = requiredSkill.getSkill().getSkill();
                skillName.add(skill);
            }

            // 이중 for문을 방지하기 위해, 배열을 하나의 문자열로 만들기
            String skillListString = String.join(" · ", skillName);

            // 주소 포맷
            String Address = requiredPosition.getJobOpening().getCompAddress();
            String compAddressFormat = Split.AddressSplit(Address);

            JobOpeningMainDTO jobOpeningMainDTO = JobOpeningMainDTO.builder()
                    .jobOpeningId(requiredPosition.getJobOpening().getId())
                    .titleList(requiredPosition.getJobOpening().getTitle())
                    .compNameList(requiredPosition.getJobOpening().getUser().getUserName())
                    .compAddressList(compAddressFormat)
                    .careerList(requiredPosition.getJobOpening().getCareer())
                    .careerYearList(requiredPosition.getJobOpening().getCareerYear())
                    .skillList(skillListString)
                    .build();
            jobOpeningMainDTOList.add(jobOpeningMainDTO);
        }

        return jobOpeningMainDTOList;
    }

    public List<JobOpeningMainDTO> 메인화면() {
        List<JobOpening> jobOpeningList = jobOpeningRepository.mfindByAllJoinJobOpeningAndUser();

        // jobOpening을 담기 위한 List
        List<JobOpeningMainDTO> jobOpeningMainDTOList = new ArrayList<>();
        for (JobOpening jobOpening : jobOpeningList) {

            // skillName을 담기 위한 List
            List<String> skillName = new ArrayList<>();
            for (RequiredSkill requiredSkill : jobOpening.getRequiredSkillList()) {
                String skill = requiredSkill.getSkill().getSkill();
                skillName.add(skill);
            }

            // 이중 for문을 방지하기 위해, 배열을 하나의 문자열로 만들기
            String skillListString = String.join(" · ", skillName);

            // 주소 포맷
            String Address = jobOpening.getCompAddress();
            String compAddressFormat = Split.AddressSplit(Address);

            JobOpeningMainDTO jobOpeningMainDTO = JobOpeningMainDTO.builder()
                    .jobOpeningId(jobOpening.getId())
                    .titleList(jobOpening.getTitle())
                    .compNameList(jobOpening.getUser().getUserName())
                    .compAddressList(compAddressFormat)
                    .careerList(jobOpening.getCareer())
                    .careerYearList(jobOpening.getCareerYear())
                    .skillList(skillListString)
                    .build();
            jobOpeningMainDTOList.add(jobOpeningMainDTO);
        }
        return jobOpeningMainDTOList;
    }

    public JobOpeningDetailDTO 상세채용공고(Integer id) {
        Optional<JobOpening> jobOpeningOP = jobOpeningRepository.findById(id);
        if (jobOpeningOP.isPresent()) {
            JobOpening jobOpening = jobOpeningOP.get();

            // 연력 날짜포맷

            Date compCreatedAt = jobOpening.getUser().getCompHistory();
            String compCreatedAtFormat = FormatDate.formatDateYear(compCreatedAt);

            // task - content - 가져오기
            List<Task> taskList = taskRepository.mfindByJobOpeningId(jobOpening.getId());
            List<TaskContentDTO> taskDetailDTOList = new ArrayList<>();
            for (Task task : taskList) {
                TaskContentDTO taskContentDTO = TaskContentDTO.builder()
                        .taskContent(task.getTaskContent())
                        .build();
                taskDetailDTOList.add(taskContentDTO);
            }
            // quelified - qualifiedContent - 가져오기
            List<Qualified> qualifiedList = qualifiedRepository.mfindByJobOpeningId(jobOpening.getId());
            List<QualifiedContentDTO> qualifiedContentDTOList = new ArrayList<>();
            for (Qualified qualified : qualifiedList) {
                QualifiedContentDTO qualifiedContentDTO = QualifiedContentDTO.builder()
                        .qualifiedContent(qualified.getQualifiedContent())
                        .build();
                qualifiedContentDTOList.add(qualifiedContentDTO);
            }

            // required Position - name 가져오기
            List<RequiredPosition> requiredPosition = requiredPositionRepository
                    .mfindByIdJoinPosition(jobOpening.getId());
            List<PositionNameDTO> positionNameDTOList = new ArrayList<>();
            for (RequiredPosition rpList : requiredPosition) {
                PositionNameDTO positionNameDTO = PositionNameDTO.builder()
                        .positionName(rpList.getPosition().getPosition())
                        .build();
                positionNameDTOList.add(positionNameDTO);
            }

            // required Skill - name 가져오기
            List<RequiredSkill> requiredSkill = requiredSkillRepository.mfindByIdJoinSkill(jobOpening.getId());
            List<SkillNameDTO> skillNameDTOList = new ArrayList<>();
            for (RequiredSkill skill : requiredSkill) {
                String skillName = skill.getSkill().getSkill();
                SkillNameDTO skillNameDTO = SkillNameDTO.builder()
                        .skillName(skillName)
                        .build();
                skillNameDTOList.add(skillNameDTO);

            }

            // view를 하기 위한 DTO
            JobOpeningDetailDTO jobOpeningDetailDTO = JobOpeningDetailDTO.builder()
                    .jobOpeningId(jobOpening.getId())
                    .title(jobOpening.getTitle())
                    .process(jobOpening.getProcess())
                    .career(jobOpening.getCareer())
                    .careerYear(jobOpening.getCareerYear())
                    .edu(jobOpening.getEdu())
                    .compName(jobOpening.getUser().getUserName())
                    .compAddress(jobOpening.getCompAddress())
                    .compIntro(jobOpening.getUser().getCompIntro())
                    .compPicUrl(jobOpening.getUser().getCompPicUrl())
                    .compFormatCreatedAt(compCreatedAtFormat)
                    .deadLine(jobOpening.getDeadLine())
                    .createdAt(jobOpening.getCreatedAt())
                    .taskList(taskDetailDTOList)
                    .qulifiedList(qualifiedContentDTOList)
                    .requiredSkillList(skillNameDTOList)
                    .requiredPositionList(positionNameDTOList)
                    .build();

            return jobOpeningDetailDTO;
        } else {
            throw new MyException("해당 채용공고를 찾을 수 없습니다.");
        }

    }

}
