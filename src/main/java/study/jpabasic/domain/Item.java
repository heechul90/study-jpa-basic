package study.jpabasic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *  JOINED: 조인테이블 전략 -> dtype 만들어주는게 좋음(정석)
 *      ==> 장점 : 테이블 정규화, 외래 키 참조 무결성 제약조건 활용가능, 저장공간 효율화
 *      ==> 단점 : 조회시 조인을 많이 사용, 성능 저하, 조회 쿼리가 복잡함, 데이터 저장시 insert sql 2번 호출
 *  SINGLE_TABLE: 단일테이블 전략 -> dtype 무조건 들어간다
 *      ==> 장점 : 조인이 필요 없으므로 일반적으로 조회 성능이 빠름, 조회 쿼리가 단순함
 *      ==> 단점 : 자식 엔티티가 매핑한 컬럼은 모두 null 허용, 단일 테이블에 모든 것을 저장하무로 테이블이 커질수 있는 상황에 따라서 조회 서능이 오히려 느려질수 잇다.
 *  TABLE_PER_CLASS: 테이블을 다 따로 만든다. -> @DiscriminatorColumn 필요없어짐
 *      ==> 장 단점 필요없이 사용하지 말자!!!!
 */
@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "DIS_TYPE")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String name;
    private int price;


}
