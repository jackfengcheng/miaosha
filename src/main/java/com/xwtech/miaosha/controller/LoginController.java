package com.xwtech.miaosha.controller;

import com.xwtech.miaosha.base.CodeMsg;
import com.xwtech.miaosha.base.Response;
import com.xwtech.miaosha.service.UserService;
import com.xwtech.miaosha.util.ValidatorUtil;
import com.xwtech.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @Autowired
    private UserService userService;

    @RequestMapping("to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Response doLogin(HttpServletResponse response,LoginVo loginVo){
        logger.info(loginVo.toString());
        String inputPass = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        if(StringUtils.isEmpty(inputPass)){
            return Response.error(CodeMsg.PASSWORD_EMPTY);
        }
        if(StringUtils.isEmpty(mobile)){
            return Response.error(CodeMsg.MOBILE_EMPTY);
        }
        if(!ValidatorUtil.isMobile(mobile)){
            return Response.error(CodeMsg.MOBILE_ERROR);
        }

        userService.login(response,loginVo);
        return Response.success(true);
    }
}
