package com.scs.web.blog.service;
/*@ClassName UserService
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.util.Result;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserService {
    /*
    * 登录功能
    * */
    Result signIn(UserDto userDto);
    /*
    *获取作者
    * */
    List<User>  listUser();
    /*
    * 注册功能
    * */
    Map<String,Object> signUp(UserDto userDto);
    /*
    * 获取热门用户信息
    * */
    Result getHotUsers();
    /*
    * 根据id查询用户详情数据
    * */
    Result getUser(long id);

}
