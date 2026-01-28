package com.example.lab5.controller;

import com.example.lab5.service.CookieService;
import com.example.lab5.service.ParamService;
import com.example.lab5.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @Autowired
    CookieService cookieService;
    @Autowired
    ParamService paramService;
    @Autowired
    SessionService sessionService;
    
    @GetMapping("/account/login")
    public String login(Model model){
        String user = cookieService.getValue("user");
        if (!user.isEmpty()) {
            model.addAttribute("username", user);
            model.addAttribute("checked", true);// nút remember
        }
        return "account/login";
    }
    
    @PostMapping("/account/login")
    public String loginProcess(Model model){
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);
        
        //account giả lập
        if (un.equals("poly") && pw.equals("123")) {
            sessionService.set("username", un);
            if (rm) {
                cookieService.add("user", un, 10 * 24);
            } else {
                cookieService.remove("user");
            }
            model.addAttribute("message", "Login thành công!");
        } else {
            model.addAttribute("message", "Sai username hoặc password!");
        }
        return "account/login";
    }
}
