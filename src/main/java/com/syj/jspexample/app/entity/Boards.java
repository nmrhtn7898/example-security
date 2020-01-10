package com.syj.jspexample.app.entity;

import lombok.Data;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Boards extends Meta {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Categories categories;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Boards group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Boards parent;

    @Column(nullable = false)
    private Long seq;

    @Column(nullable = false)
    private Long depth;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

}
