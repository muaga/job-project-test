package shop.mtcoding.project.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.project.Resume.Resume;
import shop.mtcoding.project.jobpening.JobOpening;
import shop.mtcoding.project.scrap.CompScrap;
import shop.mtcoding.project.scrap.UserScrap;
import shop.mtcoding.project.suggest.Suggest;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40, unique = true)
    private String userEmailId;

    @Column(length = 60, unique = true)
    private String compEmailId;

    @Column(length = 20)
    private String userName;

    @Column(length = 80)
    private String userPassword;

    private String userPicUrl;

    private String compPicUrl;

    @Column(length = 30)
    private String compName;

    @Column(length = 100)
    private String compHistory;

    @Column(length = 100)
    private String compIntro;

    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Resume> resumeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<JobOpening> jobOpeningList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserScrap> userScrapList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CompScrap> compScrapList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Suggest> suggestList = new ArrayList<>();

    @Builder
    public User(Integer id, String userEmailId, String compEmailId, String userName, String userPassword,
            String userPicUrl, String compPicUrl, String compName, String compHistory, String compIntro,
            Timestamp createdAt, List<Resume> resumeList, List<JobOpening> jobOpeningList,
            List<UserScrap> userScrapList, List<CompScrap> compScrapList, List<Suggest> suggestList) {
        this.id = id;
        this.userEmailId = userEmailId;
        this.compEmailId = compEmailId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPicUrl = userPicUrl;
        this.compPicUrl = compPicUrl;
        this.compName = compName;
        this.compHistory = compHistory;
        this.compIntro = compIntro;
        this.createdAt = createdAt;
        this.resumeList = resumeList;
        this.jobOpeningList = jobOpeningList;
        this.userScrapList = userScrapList;
        this.compScrapList = compScrapList;
        this.suggestList = suggestList;
    }

}
