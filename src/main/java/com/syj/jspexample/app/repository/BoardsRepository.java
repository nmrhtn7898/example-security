package com.syj.jspexample.app.repository;

import com.syj.jspexample.app.entity.Boards;
import com.syj.jspexample.app.repository.custom.BoardsRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BoardsRepository extends JpaRepository<Boards, Long>, BoardsRepositoryCustom {

}
