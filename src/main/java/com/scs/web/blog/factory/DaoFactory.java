package com.scs.web.blog.factory;
/*@ClassName DaoFactory
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/6
 *@Version 1.0
 **/

import com.scs.web.blog.dao.ArticleDao;

import com.scs.web.blog.dao.Impl.ArticleDaoImpl;
import com.scs.web.blog.dao.Impl.StudentDaoImpl;
import com.scs.web.blog.dao.Impl.TopicDaoImpl;
import com.scs.web.blog.dao.Impl.UserDaoImpl;
import com.scs.web.blog.dao.StudentDao;
import com.scs.web.blog.dao.TopicDao;
import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.entity.Article;

public class DaoFactory {
    public  static StudentDao getStudentDoaInstance(){

        return new StudentDaoImpl();
    }
    public static UserDao getUserDaoInstance() {

        return new UserDaoImpl();
    }
    public  static ArticleDao getArticleDaoInstance(){
        return  new ArticleDaoImpl();
    }
    public  static TopicDao getTopicDaoInstance(){
        return  new TopicDaoImpl();
    }

}
