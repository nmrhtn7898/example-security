package com.syj.jspexample.app.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Meta {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modified;

    @Column(nullable = false, length = 1)
    private String deleted = "N";

    @Column(nullable = false, length = 1)
    private String enabled = "Y";

}
