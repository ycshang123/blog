package com.scs.web.blog.service.Impl;
/*@ClassName UserServiceImpl
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.domain.Vo.ArticleVo;
import com.scs.web.blog.domain.Vo.UserVo;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.Message;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getUserDaoInstance();
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Result signIn(UserDto userDto) {
        User user = null;
        try {
            user = userDao.findUserByMobile(userDto.getMobile());
        } catch (SQLException e) {
            logger.error("根据手机号查询用户出现异常");
        }
        if (user != null) {
            if (user.getPassword().equals(userDto.getPassword())) {
               //登录成功
                return Result.success(user);
            } else {
               //密码错误
                return Result.failure(ResultCode.USER_PASSWORD_ERROR);
            }
        } else {
            //账号错误
          return Result.failure(ResultCode.USER_ACCOUNT_ERROR);
        }
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

    @Override
    public Map<String, Object> signUp(UserDto userDto) {
        Map<String, Object> map = new HashMap<>();
        int i = 0;
        try {
            i = DaoFactory.getUserDaoInstance().insert(userDto);
        } catch (SQLException e) {
            logger.error("注册失败");
        }
        if (i == 1) {
          map.put("msg","注册成功");
        } else {
           map.put("msg","注册失败");
        }

        return map;
    }

    @Override
    public Result getHotUsers() {
       List<User> userList = null;
        try {
            userList = userDao.selectHotUsers();
        } catch (SQLException e) {
            logger.error("获取热门用户出现异常");
        }
        if(userList !=null){
            //成功并返回数据
            return  Result.success(userList);
        }else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result getUser(long id) {
        UserVo userVo = null;
        try {
            userVo = userDao.getUser(id);
        } catch (SQLException e) {
            logger.error("根据id获取用户详情出现异常");
        }
//        if(userVo !=null){
//            try{
//                List<ArticleVo> articleVoList = articleDao.s
//            }
//        }
        return null;
    }

    @Override
    public Result selectByPage(int currentPage, int count) {
        List<User> userList = null;
        try {
            userList = userDao.selectByPage(currentPage,count);
        } catch (SQLException e) {
            logger.error("分页查询用户出现异常");
        }
        if(userList !=null){
            return Result.success(userList);
        }else{
            return  Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }

    }

    @Override
    public Result selectByKeywords(String keywords) {
        List<User> userList = null;
        try {
            userList = userDao.selectByKeywords(keywords);
        } catch (SQLException e) {
            logger.error("根据关键字查询用户出现异常");
        }
        if(userList !=null){
            return  Result.success(userList);
        }else{
            return  Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
