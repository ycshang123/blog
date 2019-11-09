package com.scs.web.blog.service.Impl;/*@ClassName StudentServiceImpl
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.dao.StudentDao;
import com.scs.web.blog.entity.Student;
import com.scs.web.blog.service.StudentService;

import java.sql.SQLException;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = DaoFactory.getStudentDoaInstance();

    @Override
    public List<Student> listStudent() {
        List<Student> studentList = null;
        try {
            studentList = studentDao.selectAll();
        } catch (SQLException e) {
            System.err.println("查询所有学生出现异常");
        }
        return  studentList;
    }
}
