package com.syj.jspexample.app.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("body", "main/index.jsp");
        return "layout";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("body", "users/login.jsp");
        return "layout";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("body", "users/join.jsp");
        return "layout";
    }

    @GetMapping("/leave")
    public String leave(Model model) {
        model.addAttribute("body", "users/leave.jsp");
        return "layout";
    }

    @GetMapping("/update")
    public String update(Model model) {
        model.addAttribute("body", "users/update.jsp");
        return "layout";
    }

}
