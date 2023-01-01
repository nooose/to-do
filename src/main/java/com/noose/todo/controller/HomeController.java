package com.noose.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // TODO: 로그인 체크 후 리다이렉션

        return "redirect:/login";
    }
}
