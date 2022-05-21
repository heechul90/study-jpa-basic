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
    //@Rollback(value = false)
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

    @Test
    //@Rollback(value = false)
    public void createMemberWithEmbeddedTest() throws Exception{
        //given

        Period period = new Period(LocalDateTime.now(), LocalDateTime.now());
        Address address = new Address("sejong", "hanuridaero", "12345");
        Member member = new Member("heechul4296", "Lee", 33, RoleType.USER, "내용", period, address);

        //when
        em.persist(member);
        em.flush();
        em.clear();

        //then
        Member findMember = em.find(Member.class, member.getId());
        assertThat(findMember.getHomeAddress().getCity()).isEqualTo("sejong");
    }

    /**
     * 값 타입과 불변 객체
     * @throws Exception
     */
    @Test
    //@Rollback(value = false)
    public void createMemberWithEmbedded2Test() throws Exception{
        //given
        Address address = new Address("Sejong", "hanuridaero", "12345");
        Member member1 = new Member("heechul4296", "Lee1", 33, RoleType.USER, "내용", new Period(), address);
        em.persist(member1);

        Member member2 = new Member("heechul4296", "Lee1", 33, RoleType.USER, "내용", new Period(), address);
        em.persist(member2);

        //em.flush();
        //em.clear();

        //when
        member1.changeHomeAddress(new Address("Daejeon", address.getStreet(), address.getZipcode()));

        //then
        Member findMember1 = em.find(Member.class, member1.getId());
        Member findMember2 = em.find(Member.class, member2.getId());

        assertThat(findMember1.getHomeAddress().getCity()).isEqualTo("Daejeon");
        assertThat(findMember2.getHomeAddress().getCity()).isEqualTo("Sejong");
    }

    /**
     * 값 타입의 비교
     * @throws Exception
     */
    @Test
    public void equalTest() throws Exception{
        //given
        Address address1 = new Address("Sejong", "Hanuridaero", "123345");
        Address address2 = new Address("Sejong", "Hanuridaero", "123345");

        //then
        assertThat(address1 == address2).isFalse();
        assertThat(address1.equals(address2)).isTrue();
    }


}