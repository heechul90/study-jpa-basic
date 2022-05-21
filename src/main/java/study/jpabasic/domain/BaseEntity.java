package study.jpabasic.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseEntity {

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public void CreateInfo(String createdBy, LocalDateTime createdDate) {
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public void LastModifiedInfo(String lastModifiedBy, LocalDateTime lastModifiedDate) {
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

}
