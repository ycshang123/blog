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
    * 获取热门文章
    *
    * */
    Result getHotArticles();
    /*
    获取所有图书信息
     */
    List<Article> listArticle() ;
    /*
    * 获取文章详情
    * */
    Result getArticle(long id);
    /*
    * 获取分页文章
    * */
    Result getArticleByPage(int currentPage,int count);
    /*
    * 根据标题或摘要模糊查询文章
    * */
    Result selectByKeywords(String keywords);
}
