package com.xwtech.miaosha.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {



    @RequestMapping("/to_list")
    public String list(Model model) {
        model.addAttribute("user", "1111");
        return "goods_list";
    }

}
