package com.scs.web.blog.service;
/*@ClassName UserService
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import com.scs.web.blog.domain.UserDto;

import java.util.Map;

public interface UserService {
    /*
    * 登录功能
    * */
    Map<String,Object> signIn(UserDto userDto);
}
