package shop.mtcoding.project.Apply;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.project.Resume.Resume;
import shop.mtcoding.project.jobpening.JobOpening;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "apply_tb")
@Entity
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String applyState;
    private String applyAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobOpening jobOpening;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @Builder
    public Apply(Integer id, String applyState, String applyAnswer, JobOpening jobOpening, Resume resume) {
        this.id = id;
        this.applyState = applyState;
        this.applyAnswer = applyAnswer;
        this.jobOpening = jobOpening;
        this.resume = resume;
    }

}
