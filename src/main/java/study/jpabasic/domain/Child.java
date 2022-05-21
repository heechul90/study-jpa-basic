package study.jpabasic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Child {

    @Id @GeneratedValue
    @Column(name = "child_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    public Child(String name) {
        this.name = name;
    }

    public Child(String name, Parent parent) {
        this.name = name;
        this.parent = parent;
    }

    public void addParent(Parent parent) {
        this.parent = parent;
    }
}
