package study.jpabasic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name = "MEMBER_PRODUCT_SEQ_GENERATOR",
        sequenceName = "MEMBER_PRODUCT_SEQ",
        initialValue = 1, allocationSize = 100
)
@Getter
@NoArgsConstructor
public class MemberProduct {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_PRODUCT_SEQ")
    @Column(name = "member_product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int count;
    private int price;
    private LocalDateTime orderDateTime;
}
