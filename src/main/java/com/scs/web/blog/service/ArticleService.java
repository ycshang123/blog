package com.scs.web.blog.service;/*@ClassName ArticleService
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/10
 *@Version 1.0
 **/

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.util.Result;

import java.util.List;

public interface ArticleService {
    /*
    获取所有图书信息
     */
    List<Article> listArticle() ;
    /*
    * 获取文章详情
    * */
    Result getArticle(long id);
}
