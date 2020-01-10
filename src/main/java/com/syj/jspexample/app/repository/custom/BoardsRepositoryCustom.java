package com.syj.jspexample.app.repository.custom;

import com.syj.jspexample.app.entity.Boards;

import java.util.List;

public interface BoardsRepositoryCustom {

    List<Boards> findBoardsPaging(int page, int pageSize, String category, String start, String end);

    Long countBoards(String category, String start, String end);

    Long findGroupMaxSeq(Long id);
}
