package com.syj.jspexample.app.controller.api;

import com.syj.jspexample.app.dto.*;
import com.syj.jspexample.app.entity.Boards;
import com.syj.jspexample.app.service.CategoriesService;
import com.syj.jspexample.app.service.BoardsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardsApiController {

    private final BoardsService boardsService;

    private final CategoriesService categoriesService;

    @GetMapping(
            value = "/api/boards/{type:free|users}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PagingResult paging(@PathVariable String type, Paging request) {
        List<ListResponse> response = boardsService
                .paging(type, request)
                .stream()
                .map(ListResponse::new)
                .collect(Collectors.toList());
        Long count = boardsService.count(type, request);
        return new PagingResult<>(response, new PagingStatus(request, count, response.size()), new Status("200", "SUCCESS"));
    }

    @PostMapping(
            value = "/api/boards/{type:free|users}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Result insert(@PathVariable String type, @RequestBody @Valid InsertRequest request) {
        Boards boards = new Boards();
        Long groupId = request.getGroup();
        Long parentId = request.getParent();
        boards.setCategories(categoriesService.one(type));
        if (!Objects.isNull(groupId)) {
            Boards group = new Boards();
            group.setId(groupId);
            boards.setGroup(group);
        }
        if (!Objects.isNull(parentId)) {
            Boards parent = new Boards();
            parent.setId(parentId);
            boards.setParent(parent);
        }
        boards.setTitle(request.getTitle());
        boards.setContent(request.getContent());
        boards.setDepth(request.getDepth());
        boards = boardsService.insert(boards);
        return new Result<>(new InsertResponse(boards), new Status("200", "SUCCESS"));
    }

    /** 해당 컨트롤러에서 사용하는 요청, 응답 DTO 선언 */
    @Data
    private static class ListResponse {
        private Long id;
        private String title;
        private String content;
        private String type;
        private Long group;
        private Long parent;
        private Long depth;
        private Long seq;
        private LocalDateTime insertDate;
        private LocalDateTime updateDate;
        private ListResponse(Boards boards) {
            this.id = boards.getId();
            this.title = boards.getTitle();
            this.content = boards.getContent();
            this.type = boards.getCategories().getName();
            this.group = Objects.isNull(boards.getGroup()) ? null : boards.getGroup().getId();
            this.parent = Objects.isNull(boards.getParent()) ? null : boards.getParent().getId();
            this.depth = boards.getDepth();
            this.seq = boards.getSeq();
            this.insertDate = boards.getCreated();
            this.updateDate = boards.getModified();
        }
    }

    @Data
    private static class InsertRequest {
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        @NotNull
        private Long depth;
        private Long group;
        private Long parent;
    }

    @Data
    private static class InsertResponse {
        private Long id;
        private String type;
        private Long group;
        private Long parent;
        private Long depth;
        private Long seq;
        private LocalDateTime insertDate;
        private InsertResponse(Boards boards) {
            this.id = boards.getId();
            this.type = boards.getCategories().getName();
            this.group = Objects.isNull(boards.getGroup()) ? null : boards.getGroup().getId();
            this.parent = Objects.isNull(boards.getParent()) ? null : boards.getParent().getId();
            this.depth = boards.getDepth();
            this.seq = boards.getSeq();
            this.insertDate = boards.getCreated();
        }
    }

}
