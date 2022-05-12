package study.jpabasic.repository;

import org.springframework.stereotype.Repository;
import study.jpabasic.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }
}
