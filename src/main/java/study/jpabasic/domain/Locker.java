package study.jpabasic.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "LOCKER_SEQ_GENERATOR",
        sequenceName = "LOCKER_SEQ",
        initialValue = 1, allocationSize = 100
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Locker {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCKER_SEQ_GENERATOR")
    @Column(name = "locker_id")
    private Long id;

    @Column(name = "locker_name")
    private String name;
}
