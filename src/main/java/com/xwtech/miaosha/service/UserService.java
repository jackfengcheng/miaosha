package com.xwtech.miaosha.service;

import com.xwtech.miaosha.base.CodeMsg;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.vo.LoginVo;

public interface UserService {

    public User getById(Integer id);

    public CodeMsg login(LoginVo loginVo);
}
