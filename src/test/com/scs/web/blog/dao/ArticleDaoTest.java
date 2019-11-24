package com.scs.web.blog.dao;

import com.scs.web.blog.domain.Vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ArticleDaoTest {
    private static Logger logger = LoggerFactory.getLogger(ArticleDaoTest.class);
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();

    @Test
    public void batchInsert() {
        try {
            int[] result = articleDao.batchInsert(JSoupSpider.getArticle());
            if(result.length != 0){
                logger.info("批量图书增加成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
//            logger.error("批量图书增加出现异常");
        }
    }

    @Test
    public void getArticle() throws SQLException {
        ArticleVo articleVo = articleDao.getArticle(3);
        System.out.println(articleVo);
    }

    @Test
    public void selectHotArticles() throws SQLException {
        List<ArticleVo> articleVoList = articleDao.selectHotArticles();
        System.out.println(articleVoList.size());
    }
}

