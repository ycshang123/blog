package com.scs.web.blog.dao;

import com.scs.web.blog.dao.Impl.UserDaoImpl;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.Impl.UserServiceImpl;
import com.scs.web.blog.util.JSoupSpider;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class UserDaoTest {
    private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
    private UserDao userDao = DaoFactory.getUserDaoInstance();

    @Test
    public void batchInsert() {
            try {
                int[] result = userDao.batchInsert(JSoupSpider.getUsers());
                if(result.length != 0){
                    logger.info("批量新增用户成功");
                }
            } catch (SQLException e) {
                logger.error("批量新增用户出现异常");
            }
        }

    @Test
    public void findUserByMobile() throws SQLException {
        User user = new UserDaoImpl().findUserByMobile("13972475335");
        if (user != null){
            System.out.println("ok");
        }else {
            System.out.println("no");
        }

    }
}
