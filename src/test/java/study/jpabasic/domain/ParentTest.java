package study.jpabasic.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ParentTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void createParentTest() throws Exception{
        //given
        Child child1 = new Child("child1");
        Child child2 = new Child("child2");

        Parent parent = new Parent("parent");
        parent.addChild(child1);
        parent.addChild(child2);

        //when
        em.persist(parent);

        em.flush();
        em.clear();

        //then
        Parent findParent = em.find(Parent.class, parent.getId());
        em.remove(findParent);
    }

}