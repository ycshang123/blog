package com.scs.web.blog.dao.Impl;/*@ClassName StudentDaoImpl
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/6
 *@Version 1.0
 **/

import ch.qos.logback.core.db.dialect.DBUtil;
import com.scs.web.blog.dao.StudentDao;
import com.scs.web.blog.entity.Student;
import com.scs.web.blog.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public int insert(Student student) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_student VALUES (NULL,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, student.getUsername());
        pstmt.setString(2, student.getAvatar());
        pstmt.setTimestamp(3, Timestamp.valueOf(student.getCreateTime()));
        int n = pstmt.executeUpdate();
        connection.commit();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int[] batchInsert(List<Student> studentList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_student VALUES (NULL,?,?,?,?) ";
        PreparedStatement pstmt =  connection.prepareStatement(sql);
        connection.setAutoCommit(false);
       studentList.forEach(student -> {
           try {
               pstmt.setString(1,student.getUsername());
               pstmt.setString(2,student.getAvatar());
               pstmt.setTimestamp(3, Timestamp.valueOf(student.getCreateTime()));
               pstmt.setString(4,student.getIntroduce());
               //预处理事务集添加批处理
               pstmt.addBatch();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       });
        int[] result = pstmt.executeBatch();
        //提交
        connection.commit();
        //关闭
        pstmt.close();
        connection.close();
        return result;
    }

    @Override
    public List<Student> selectAll() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_student ORDER BYid DESC";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setUsername(rs.getString("username"));
            student.setAvatar(rs.getString("avatar"));
            Timestamp timestamp =(rs.getTimestamp("create_time"));
            student.setCreateTime(timestamp.toLocalDateTime());
            studentList.add(student);

        }
        return  studentList;
    }
}
