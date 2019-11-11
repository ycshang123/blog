package com.scs.web.blog.service;/*@ClassName ArticleService
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/10
 *@Version 1.0
 **/

import com.scs.web.blog.entity.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleService {
    /*
    获取所有图书信息
     */
    List<Article> listArticle() ;
}
