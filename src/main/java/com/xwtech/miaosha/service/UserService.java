package com.xwtech.miaosha.service;

import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    public User getById(Long id);

    public boolean login( HttpServletResponse response, LoginVo loginVo);
}
