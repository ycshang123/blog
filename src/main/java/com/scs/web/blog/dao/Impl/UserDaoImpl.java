package com.scs.web.blog.dao.Impl;/*@ClassName UserDaoImpl
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import ch.qos.logback.core.db.dialect.DBUtil;
import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.domain.Vo.UserVo;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.util.BeanHandler;
import com.scs.web.blog.util.DbUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.plaf.synth.ColorType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static Logger loggger = LoggerFactory.getLogger(UserDaoImpl.class);
    @Override
    public int[] batchInsert(List<User> userList) throws SQLException {
        Connection connection = DbUtil.getConnection();
         String sql = "INSERT INTO t_user (mobile,password,nickname,avatar,gender,birthday,introduction,follows,create_time) VALUES (?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        userList.forEach(user -> {
            try {
                pstmt.setString(1, user.getMobile());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getNickname());
                pstmt.setString(4, user.getAvatar());
                pstmt.setString(5, user.getGender());
                //日期的设置，可以使用setObject
                pstmt.setObject(6, user.getBirthday());
                pstmt.setString(7, user.getIntroduction());
                pstmt.setInt(8,user.getFollows());
                pstmt.setObject(9,user.getCreateTime());
                pstmt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        int[] result = pstmt.executeBatch();
        connection.commit();
       DbUtil.close(connection,pstmt);
        return  result;
    }

    @Override
    public List<User> selectByKeywords(String keywords) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE nickname LIKE ? OR introduction LIKE ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,"%"+keywords+"%");
        pst.setString(2,"%"+keywords+"%");
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.converUser(rs);
        DbUtil.close(connection,pst,rs);
        return userList;
    }

    @Override
    public List<User> selectByPage(int currentPage, int count) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user LIMIT ?,?" ;
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1,(currentPage -1) *count);
        pst.setInt(2,count);
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.converUser(rs);
        return userList;
    }

    @Override
    public User findUserByMobile(String mobile) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE mobile = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, mobile);
        ResultSet rs = pstmt.executeQuery();
        User user = BeanHandler.converUser(rs).get(0);
        DbUtil.close(connection,pstmt,rs);
        return user;
    }


    @Override
    public int insert(UserDto userDto) throws SQLException {
     Connection connection = DbUtil.getConnection();
     connection.setAutoCommit(false);
     String sql ="INSERT INTO t_user(mobile,password) VALUES(?,?)";
     PreparedStatement pstmt = connection.prepareStatement(sql);
     pstmt.setString(1,userDto.getMobile());
     pstmt.setString(2,userDto.getPassword());
     int n = pstmt.executeUpdate();
     connection.commit();
     return n;
    }

    @Override
    public List<User> selectHotUsers() throws SQLException {
       Connection connection = DbUtil.getConnection();
       String sql = "SELECT * FROM t_user ORDER BY fans DESC LIMIT 10 ";
       PreparedStatement pst = connection.prepareStatement(sql);
       ResultSet rs = pst.executeQuery();
       List<User> userList = BeanHandler.converUser(rs);
       DbUtil.close(connection,pst,rs);
        return  userList;
    }

    @Override
    public UserVo getUser(long id) throws SQLException {
        Connection connection  =   DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1,id);
        ResultSet rs = pst.executeQuery();
        UserVo userVo = new UserVo();
        User user = BeanHandler.converUser(rs).get(0);
        userVo.setUser(user);
        DbUtil.close(connection,pst,rs);
        return  userVo;

    }
}

