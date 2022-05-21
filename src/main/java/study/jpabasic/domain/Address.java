package study.jpabasic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 값 타입은 불변으로 만들자(절대 수정 못하게 만든다)
 * 수정이 필하다면 다시 생성하는게 좋다
 */
@Embeddable
@Getter
@NoArgsConstructor
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
