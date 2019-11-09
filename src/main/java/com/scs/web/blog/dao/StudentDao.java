package com.scs.web.blog.dao;/*@ClassName StudentDao
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/6
 *@Version 1.0
 **/

import com.scs.web.blog.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    /*
    * 添加学生
    * */
    int insert(Student student) throws SQLException;
    /**
     * 批量新增学生
     */

    int[] batchInsert(List<Student> studentList) throws  SQLException;
    List<Student> selectAll() throws SQLException;
}
