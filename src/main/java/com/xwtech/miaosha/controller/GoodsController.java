package com.xwtech.miaosha.controller;


import com.xwtech.miaosha.base.CookieKey;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private UserService userService;

    @RequestMapping("/to_list")
    public String list(Model model,HttpServletResponse response,User user) {
        model.addAttribute("user",user);
        return "goods_list";
    }

}
