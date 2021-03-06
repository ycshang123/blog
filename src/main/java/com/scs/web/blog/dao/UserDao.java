package com.scs.web.blog.dao;/*@ClassName UserDao
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.domain.Vo.UserVo;
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
    * 注册用户
    * */
    int insert(UserDto userDto) throws  SQLException;
    /*
    * 查询热门用户
    * */
    List<User> selectHotUsers() throws  SQLException;
    /*
    * 根据id查询用户
    * */
    UserVo getUser(long id) throws SQLException;
    /*
    * 查询分页用户
    * */
    List<User> selectByPage(int currentPage ,int count) throws  SQLException;
    /*
    * 模糊搜索用户
    * */
    List<User> selectByKeywords(String keywords) throws  SQLException;
}
