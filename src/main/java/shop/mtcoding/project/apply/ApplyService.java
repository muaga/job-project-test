package shop.mtcoding.project.apply;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.resume.Resume;

@Service
public class ApplyService {

    @Autowired
    private ApplyRepository applyRepository;

    // user_ 공고 지원
    @Transactional
    public void 지원(ApplyRequest.ApplySaveDTO applySaveDTO) {
        Apply apply = Apply.builder()
                .resume(Resume.builder().id(applySaveDTO.getSelectResumeId()).build())
                .jobOpening(JobOpening.builder().id(applySaveDTO.getSelectJobOpeningId()).build())
                .build();
        applyRepository.save(apply);
    }

}