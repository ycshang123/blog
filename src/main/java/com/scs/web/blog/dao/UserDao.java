package com.scs.web.blog.dao;/*@ClassName UserDao
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import com.scs.web.blog.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    /*
    * 批量插入用户
    * */
    int[] batchInsert(List<User> userList) throws SQLException;
    /*
    * 根据手机号查找用户
    * */
    User findUserByMobile(String mobile) throws  SQLException;
    /*
    * 获取作者
    * */
    List<User> sellectAll() throws  SQLException;

}
