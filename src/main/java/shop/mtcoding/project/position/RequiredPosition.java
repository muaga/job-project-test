package shop.mtcoding.project.position;

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
import shop.mtcoding.project.jobopening.JobOpening;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "required_position_tb")
@Entity
public class RequiredPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobOpening jobOpening;

    @Builder
    public RequiredPosition(Integer id, JobOpening jobOpening, Position position) {
        this.id = id;
        this.jobOpening = jobOpening;
        this.position = position;
    }

}
