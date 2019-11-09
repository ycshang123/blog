package com.scs.web.blog.factory;/*@ClassName ServiceFactory
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/7
 *@Version 1.0
 **/

import com.scs.web.blog.service.Impl.StudentServiceImpl;
import com.scs.web.blog.service.Impl.UserServiceImpl;
import com.scs.web.blog.service.StudentService;
import com.scs.web.blog.service.UserService;

public class ServiceFactory {
    public  static StudentService getStudentServiceInstance(){
        return  new StudentServiceImpl();
    }
    public  static UserService getUserServiceInstance(){
        return  new UserServiceImpl();
    }
}
