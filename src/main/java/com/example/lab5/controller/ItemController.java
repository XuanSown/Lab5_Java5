package com.example.lab5.controller;

import com.example.lab5.utils.DB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    @GetMapping("/item/index")
    public String list(Model model){
        model.addAttribute("items", DB.items.values());
        return "item/index";
    }
}
