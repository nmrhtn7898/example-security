package com.syj.jspexample.app.controller.view;

import com.syj.jspexample.app.dto.Paging;
import com.syj.jspexample.app.entity.Boards;
import com.syj.jspexample.app.service.BoardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardsController {

    private final BoardsService boardsService;

    @GetMapping("/{type:free|users}/list")
    public String list(@PathVariable String type, @Valid Paging domain, Model model) {
        List<Boards> list = boardsService.paging(type, domain);
        model.addAttribute("body", "freeboards/list.jsp");
        return "layout";
    }

}
