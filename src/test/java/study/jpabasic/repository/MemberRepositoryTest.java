package study.jpabasic.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.jpabasic.domain.Member;
import study.jpabasic.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    void memberTest() {
        //given
        Member member = new Member("memberA");

        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedId);

        //then
        Assertions.assertThat(findMember.getUsername()).isEqualTo("memberA");
    }

    @Test
    @Rollback(value = false)
    void jpaTest() {
        Team team = new Team("team1");
        em.persist(team);

        Member member = new Member("memberA");

        em.persist(member);

        Member findMember = em.find(Member.class, member.getId());


        //em.flush();
        //em.clear();
    }
}