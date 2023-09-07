package shop.mtcoding.project.PositionAndSkill;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.project.position.Position;
import shop.mtcoding.project.skill.Skill;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "position_and_skill_tb")
@Entity
public class PositionAndSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Position position;

    @ManyToOne
    private Skill skill;

    @Builder
    public PositionAndSkill(Integer id, Position position, Skill skill) {
        this.id = id;
        this.position = position;
        this.skill = skill;
    }

}
