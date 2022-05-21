package study.jpabasic.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 100
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(length = 60)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Lob
    private String description;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "work_city")),
            @AttributeOverride(name = "street", column = @Column(name = "work_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "work_zipcode"))
    })
    private Address workAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locker_id")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Member(String username) {
        this.username = username;
    }

    public Member(String loginId, String username, Integer age, RoleType roleType, String description) {
        this.loginId = loginId;
        this.username = username;
        this.age = age;
        this.roleType = roleType;
        this.description = description;
    }

    public Member(String loginId, String username, Integer age, RoleType roleType, String description, Team team) {
        this.loginId = loginId;
        this.username = username;
        this.age = age;
        this.roleType = roleType;
        this.description = description;
        this.team = team;
    }

    public Member(String loginId, String username, Integer age, RoleType roleType, String description, Period workPeriod, Address homeAddress) {
        this.loginId = loginId;
        this.username = username;
        this.age = age;
        this.roleType = roleType;
        this.description = description;
        this.workPeriod = workPeriod;
        this.homeAddress = homeAddress;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", roleType=" + roleType +
                ", description='" + description + '\'' +
                '}';
    }
}
