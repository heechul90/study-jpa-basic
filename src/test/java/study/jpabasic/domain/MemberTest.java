package study.jpabasic.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void creatMemberTest() throws Exception{
        //given
        Team team = new Team("teamA");
        em.persist(team);

        Member member = new Member("loginId", "username", 33, RoleType.USER, "description", team);
        member.CreateInfo("username", LocalDateTime.now());

        //when
        em.persist(member);
        em.flush();
        em.clear();

        //then
        Member findMember = em.find(Member.class, member.getId());
        System.out.println("findMember = " + findMember);
        assertThat(findMember.getUsername()).isEqualTo("username");

        System.out.println("findMember.getTeam().getName() = " + findMember.getTeam().getName());
        assertThat(findMember.getTeam().getName()).isEqualTo("teamA");
    }


}