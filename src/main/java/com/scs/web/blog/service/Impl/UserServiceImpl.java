package com.scs.web.blog.service.Impl;/*@ClassName UserServiceImpl
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private  UserDao userDao = DaoFactory.getUserDaoInstance() ;
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public Map<String, Object> signIn(UserDto userDto) {
        User user = null;
        Map<String, Object> map = new HashMap<>();
        try {
            user = userDao.findUserByMobile(userDto.getMobile());
        } catch (SQLException e) {
            logger.error("根据手机号查询用户出现异常");
        }
        if (user != null) {
            if (user.getPassword().equals(userDto.getPassword())) {
                map.put("msg", "登录成功");
                map.put("data", user);
            } else {
                map.put("msg", "密码错误");
            }
        } else {
            map.put("msg", "手机号不存在");
        }
        return map;
    }

    @Override
    public List<User> listUser() {
    List<User> userList = new ArrayList<>();
        try {
            userList = userDao.sellectAll();
        } catch (SQLException e) {
            logger.error("用户获取信息失败");
        }
        logger.info("作者信息获取成功");
        return userList;
    }
}
