package com.syj.jspexample.app.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Comments extends Meta {

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Comments group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Comments parent;

    @Column(nullable = false)
    private Long seq;

    @Column(nullable = false)
    private Long depth;

    @Column(nullable = false)
    private String content;


}
