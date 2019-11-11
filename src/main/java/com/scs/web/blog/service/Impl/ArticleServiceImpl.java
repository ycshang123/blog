package com.scs.web.blog.service.Impl;/*@ClassName ArticleServiceImpl
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/10
 *@Version 1.0
 **/

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    private  static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Override
    public List<Article> listArticle()  {
        List<Article> articleList = new ArrayList<>();
        try {
            articleList = articleDao.selectAll();
        } catch (SQLException e) {
            logger.error("查询出现异常");
        }
        logger.info("查询成功");

        return articleList;
    }
}
