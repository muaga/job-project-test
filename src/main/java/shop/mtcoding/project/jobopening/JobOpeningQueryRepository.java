package shop.mtcoding.project.jobopening;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningMainDTO;

@Repository
public class JobOpeningQueryRepository {

    @Autowired
    private EntityManager em;

    public List<JobOpeningMainDTO> findByIdJoinPositionAndSkill(List<Integer> positionIdList,
            List<Integer> skillIdList) {

        String sql = "SELECT DISTINCT j.* FROM job_opening_tb j LEFT JOIN required_position_tb rp ON j.id = rp.job_opening_id LEFT JOIN required_skill_tb rs ON j.id = rs.job_opening_id ";

        // 포지션 조건이 있다면
        if (!positionIdList.isEmpty()) {
            for (int i = 0; i < positionIdList.size(); i++) {
                if (i == 0) {
                    sql += "where rp.position_id = :positionId0";
                } else {
                    sql += "or rp.position_id = :positionId" + i + " ";
                }
            }
        }

        // 스킬 조건을 추가
        if (!skillIdList.isEmpty()) {
            if (positionIdList.isEmpty()) {
                // 포지션 조건이 없으면 WHERE 키워드를 추가하고 시작
                sql += "WHERE ";
            } else {
                // 이미 포지션 조건이 추가되었으면 AND 연산자로 스킬 조건 추가
                sql += "AND ";
            }
            for (int i = 0; i < skillIdList.size(); i++) {
                if (i == 0) {
                    sql += "rp.skill_id = :skillId0";
                } else {
                    sql += "or rp.skill_id = :skillId" + i + " ";
                }
            }
        }

        // QLRM매핑
        Query query = em.createNativeQuery(sql);
        // // 매핑
        // Query query = em.createNativeQuery(sql, Posting.class);
        // for (int i = 0; i < skillnameList.size(); i++) {
        // query.setParameter("skillname" + i, skillnameList.get(i));
        // }
        for (int i = 0; i < positionIdList.size(); i++) {
            query.setParameter("positionId" + i, positionIdList.get(i));
        }
        for (int i = 0; i < skillIdList.size(); i++) {
            query.setParameter("skillId" + i, skillIdList.get(i));
        }

        JpaResultMapper mapper = new JpaResultMapper();
        List<JobOpeningMainDTO> jobOpeningMainDTOList = mapper.list(query, JobOpeningMainDTO.class);

        return (List<JobOpeningMainDTO>) query.getResultList();
    }

}

// 공고에서 요구하는 스킬을 조인해서 조회하는 쿼리
// public List<Posting> joinSkillPosting(List<String> skillnameList) {
// String sql = "select DISTINCT posting_tb.* from skill_tb " +
// "inner join postingskill_tb on skill_tb.id = postingskill_tb.skill_id " +
// "inner join posting_tb on postingskill_tb.posting_id = posting_tb.id ";
// // 반복문을 통해 (스킬이름)체크박스에 체크된 횟수만큼 스킬이름을 or로 검색
// for (int i = 0; i < skillnameList.size(); i++) {
// if (i == 0) {
// sql += "where skill_tb.skillname = :skillname" + i + " ";
// } else {
// sql += "or skill_tb.skillname = :skillname" + i + " ";
// }
// }
// // 매핑
// Query query = em.createNativeQuery(sql, Posting.class);
// for (int i = 0; i < skillnameList.size(); i++) {
// query.setParameter("skillname" + i, skillnameList.get(i));
// }
// // 조회된 결과를 리스트에 담아서 리턴
// return (List<Posting>) query.getResultList();
// }

// SELECT DISTINCT j.*
// FROM job_opening_tb j
// LEFT JOIN required_position_tb rp ON j.id = rp.job_opening_id
// LEFT JOIN required_skill_tb rs ON j.id = rs.job_opening_id
// WHERE rp.position_id IN(1, 2)
// AND rs.skill_id IN(1, 2);

// String sql = "SELECT DISTINCT j.* FROM job_opening j LEFT JOIN
// required_position_list rp ON j.id = rp.job_opening_id LEFT JOIN
// required_skill_list rs ON j.id = rs.job_opening_id WHERE rp.position_id =
// :positionId AND rs.skill_id = :skillId;";

// SELECT DISTINCT j.* FROM job_opening_tb j LEFT JOIN required_position_tb rp
// ON j.id = rp.job_opening_id LEFT JOIN required_skill_tb rs ON j.id =
// rs.job_opening_id WHERE rp.position_id IN(1, 2) AND rs.skill_id IN(1, 2);
