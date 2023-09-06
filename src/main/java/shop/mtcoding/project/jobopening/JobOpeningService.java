package shop.mtcoding.project.jobopening;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyApiException;
import shop.mtcoding.project._core.util.FormatDate;
import shop.mtcoding.project._core.util.Split;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningDetailDTO;
import shop.mtcoding.project.position.Position;
import shop.mtcoding.project.position.PositionResponse.PositionNameDTO;
import shop.mtcoding.project.position.RequiredPosition;
import shop.mtcoding.project.position.RequiredPositionRepository;
import shop.mtcoding.project.qualified.Qualified;
import shop.mtcoding.project.qualified.QualifiedRepository;
import shop.mtcoding.project.qualified.QualifiedResponse.QualifiedContentDTO;
import shop.mtcoding.project.scrap.UserScrap;
import shop.mtcoding.project.scrap.UserScrapRequest.UserScrapDTO;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.RequiredSkillRepository;
import shop.mtcoding.project.skill.Skill;
import shop.mtcoding.project.skill.SkillRepository;
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

    public JobOpeningDetailDTO 상세채용공고(Integer id) {
        Optional<JobOpening> jobOpeningOP = jobOpeningRepository.findById(id);
        if (jobOpeningOP.isPresent()) {
            JobOpening jobOpening = jobOpeningOP.get();

            // 연력 날짜포맷
            String compCreatedAt = jobOpening.getUser().getCompHistory();
            String compCreatedAtFormat = Split.YearDateSplit(compCreatedAt);

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
                PositionNameDTO dtos = PositionNameDTO.builder()
                        .positionName(rpList.getPosition().getPosition())
                        .build();
                positionNameDTOList.add(dtos);
            }

            // required Skill - name 가져오기
            List<RequiredSkill> requiredSkill = requiredSkillRepository.mfindByIdJoinSkill(jobOpening.getId());
            List<SkillNameDTO> skillNameDTOList = new ArrayList<>();
            for (RequiredSkill rsList : requiredSkill) {
                SkillNameDTO dtos = SkillNameDTO.builder()
                        .skillName(rsList.getSkill().getSkill())
                        .build();
                skillNameDTOList.add(dtos);
            }

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
            throw new MyApiException("해당 채용공고를 찾을 수 없습니다.");
        }

    }

}
