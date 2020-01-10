package com.syj.jspexample.app.service;

import com.syj.jspexample.app.dto.Paging;
import com.syj.jspexample.app.entity.Boards;
import com.syj.jspexample.app.repository.BoardsRepository;
import com.syj.jspexample.app.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardsService {

    private final BoardsRepository boardsRepository;

    @Transactional(readOnly = true)
    public List<Boards> paging(String type, Paging domain) {
        int page = Math.max(domain.getPage() - 1, 0);
        return boardsRepository
                .findBoardsPaging(page, domain.getPageSize(), type,
                        DateUtils.dateTimeToStr(domain.getStartDate()), DateUtils.dateTimeToStr(domain.getEndDate()));
    }

    public Long count(String type, Paging domain) {
        return boardsRepository.countBoards(
                type,
                DateUtils.dateTimeToStr(domain.getStartDate()),
                DateUtils.dateTimeToStr(domain.getEndDate())
        );
    }

    @Transactional(readOnly = true)
    public Boards one(Long id) {
        return boardsRepository.findById(id).orElseGet(Boards::new);
    }

    public Boards insert(Boards boards) {
        Boards group = boards.getGroup();
        if (Objects.isNull(group)) {
            boards.setSeq(1L);
        } else {
            boards.setSeq(boardsRepository.findGroupMaxSeq(group.getId()));
        }
        return boardsRepository.save(boards);
    }


}
