package com.scs.web.blog.dao;/*@ClassName ArticleDao
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/10
 *@Version 1.0
 **/

import com.scs.web.blog.domain.Vo.ArticleVo;
import com.scs.web.blog.entity.Article;


import java.sql.SQLException;
import java.util.List;

public interface ArticleDao {

    /*
    实现图书的批量增加
     */
    int[] batchInsert(List<Article> articleList) throws SQLException;

    /*
    查找图书
     */
    List<Article> selectAll() throws SQLException;
    /*
    * 查询热门文章
    * */
    List<ArticleVo> selectHotArticles() throws  SQLException;
    /*
    * 根据id查找图书
    * */
    ArticleVo getArticle(long id) throws SQLException;
    /*
    * 根据作者id查询所有文章
    * */
    List<ArticleVo> selectByUserId(long userId) throws  SQLException;
    /*
    * 根据专题id查询所有文章
    * */
    List<ArticleVo> selectByTopicId(long topicId) throws  SQLException;
    /*
    * 分页获得文章数据
    * */
    List<ArticleVo> selectByPage(int currentPage,int count) throws SQLException;
    /*
    * 根据关键字模糊查询所有文章
    * */
    List<ArticleVo> selectByKeywords(String keywords) throws  SQLException;
}
