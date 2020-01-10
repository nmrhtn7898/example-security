package com.syj.jspexample.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Categories {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(
            mappedBy = "categories",
            cascade = CascadeType.ALL
    )
    private List<Boards> boards = new ArrayList<>();

    public void addBoards(Boards boards) {
        boards.setCategories(this);
        this.boards.add(boards);
    }

}
