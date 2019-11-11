package com.scs.web.blog.dao;/*@ClassName ArticleDao
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/10
 *@Version 1.0
 **/

import com.scs.web.blog.entity.Article;


import java.sql.SQLException;
import java.util.List;

public interface ArticleDao {

    int[] batchInsert(List<Article> articleList) throws SQLException;
    List<Article> selectAll() throws SQLException;

}
