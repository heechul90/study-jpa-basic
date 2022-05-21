package study.jpabasic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address_history")
@Getter
@NoArgsConstructor
public class AddressEntity {

    @Id @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    private Address address;

    public AddressEntity(String city, String street, String zipcode) {
        this.address = new Address(city, street, zipcode);
    }
}
