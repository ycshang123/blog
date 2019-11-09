package com.scs.web.blog.service;/*@ClassName StudentService
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import com.scs.web.blog.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService  {
    /*
    * 获取所有的学生
    * */
     List<Student> listStudent();
}
