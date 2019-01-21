package com.xwtech.miaosha.controller;

import com.xwtech.miaosha.base.CodeMsg;
import com.xwtech.miaosha.base.Response;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admini on 2019/1/21.
 */
@Controller
@RequestMapping("/demo")
public class TestController {
    @Autowired
    private UserService userService;


    @RequestMapping("/demo")
    @ResponseBody
    public String demo(){
        return "hello";
    }

    @RequestMapping("/json")
    @ResponseBody
    public Response json(){
        Map map= new HashMap();
        map.put("name","jack");
        return Response.success(map);
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Response helloError() {
        return Response.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/user")
    @ResponseBody
    public Response user() {
        User user = this.userService.getUserById(1);
        return Response.success(user);
    }
}
