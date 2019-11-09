package com.scs.web.blog.factory;
/*@ClassName DaoFactory
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/6
 *@Version 1.0
 **/

import com.scs.web.blog.dao.Impl.StudentDaoImpl;
import com.scs.web.blog.dao.Impl.UserDaoImpl;
import com.scs.web.blog.dao.StudentDao;
import com.scs.web.blog.dao.UserDao;

public class DaoFactory {
    public  static StudentDao getStudentDoaInstance(){
        return (StudentDao) new StudentDaoImpl();
    }
    public static UserDao getUserDaoInstance() {
        return new UserDaoImpl();
    }
}
