package com.syj.jspexample.app.entity;

import com.syj.jspexample.app.enums.Auth;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Users extends Meta {

    @Column(
            unique = true,
            nullable = false,
            updatable = false
    )
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Auth auth = Auth.USER;

    @OneToMany(
            mappedBy = "users",
            cascade = CascadeType.ALL
    )
    private List<Boards> boards = new ArrayList<>();

    public void addBoards(Boards boards) {
        boards.setUsers(this);
        this.boards.add(boards);
    }

}
