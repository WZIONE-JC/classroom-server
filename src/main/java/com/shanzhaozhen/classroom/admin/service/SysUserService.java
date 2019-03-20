package com.shanzhaozhen.classroom.admin.service;

import com.shanzhaozhen.classroom.bean.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SysUserService {

    Map<String, Object> getUserInfo(HttpServletRequest httpServletRequest);

    SysUser getSysUserByUsername(String username);

    SysUser getSysUserByOpenId(String openId);
}