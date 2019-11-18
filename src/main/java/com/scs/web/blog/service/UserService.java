package com.scs.web.blog.service;
/*@ClassName UserService
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserService {
    /*
    * 登录功能
    * */
    Map<String,Object> signIn(UserDto userDto);
    /*
    *获取作者
    * */
    List<User>  listUser();
    /*
    * 注册功能
    * */
    Map<String,Object> signUp(UserDto userDto);

}
