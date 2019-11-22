package com.scs.web.blog.dao.Impl;/*@ClassName ArticleDaoImpl
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/10
 *@Version 1.0
 **/

import cn.hutool.db.Db;
import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.domain.Vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.service.Impl.ArticleServiceImpl;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {
    private  static Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);
    @Override
    public int[] batchInsert(List<Article> articleList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_article(title,intro,cover,diamond,nickname,comments,likes,publish_time,user_id,content) VALUES (?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        articleList.forEach(article -> {
            try {
                pstmt.setString(1, article.getTitle());
                pstmt.setString(2, article.getIntro());
                pstmt.setString(3, article.getCover());
                pstmt.setInt(4, article.getDiamond());
                pstmt.setString(5, article.getBookname());
                pstmt.setInt(6, article.getComments());
                pstmt.setInt(7, article.getLikes());
                pstmt.setObject(8, article.getPublishtime());
                pstmt.setInt(9, article.getUserid());
                pstmt.setString(10, article.getContent());
                pstmt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        int[] result = pstmt.executeBatch();
        connection.commit();
        DbUtil.close(null, pstmt, connection);
        return result;
    }

    @Override
    public List<Article> selectAll() throws SQLException {
        List<Article> articleList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "SELECT * FROM t_article ORDER BY id DESC";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setTitle(rs.getString("title"));
            article.setIntro(rs.getString("intro"));
            article.setCover(rs.getString("cover"));
            article.setDiamond(rs.getInt("diamond"));
            article.setBookname(rs.getString("nickname"));
            article.setComments(rs.getInt("comments"));
            article.setLikes(rs.getInt("likes"));
            article.setPublishtime(rs.getTimestamp("publish_time").toLocalDateTime());
            article.setUserid(rs.getInt("user_id"));
            article.setContent(rs.getString("content"));
            articleList.add(article);
        }
        return articleList;
    }

    @Override
    public List<ArticleVo> selectHotArticles() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.nickname,b.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_user b " +
                "ON a.user_id = b.id " +
                "ORDER BY a.diamond DESC LIMIT 10";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        //调用封装方法，将结果解析成List
        List<ArticleVo> articleVoList = convert(rs);
        
        return null;
    }

    private List<ArticleVo> convert(ResultSet rs) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        try {
            while (rs.next()) {
                ArticleVo articleVo = new ArticleVo();
                articleVo.setId(rs.getLong("id"));
                articleVo.setTitle(rs.getString("title"));
                articleVo.setIntro(rs.getString("intro"));
                articleVo.setCover(rs.getString("cover"));
                articleVo.setDiamond(rs.getInt("diamond"));
                articleVo.setComments(rs.getInt("comments"));
                articleVo.setLikes(rs.getInt("likes"));
                articleVo.setPublishtime(rs.getTimestamp("publish_time").toLocalDateTime());
                articleVo.setUserid(rs.getInt("user_id"));
                articleVo.setTpyeid(rs.getInt("type_id"));
                articleVo.setContent(rs.getString("content"));
                articleVo.setNickname(rs.getString("nickname"));
                articleVo.setAvatar(rs.getString("avatar"));
                articleVoList.add(articleVo);
            }
        } catch (SQLException e) {
            logger.error("文章数据结果集解析异常");
        }
        return articleVoList;

    }

    @Override
    public ArticleVo getArticle(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.nickname,b.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_user b " +
                "ON a.user_id = b.id " +
                "WHERE a.id =? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        ArticleVo articleVo = null;
        if (rs.next()) {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setTitle(rs.getString("title"));
            article.setIntro(rs.getString("intro"));
            article.setCover(rs.getString("cover"));
            article.setDiamond(rs.getInt("diamond"));
            article.setComments(rs.getInt("comments"));
            article.setLikes(rs.getInt("likes"));
            article.setPublishtime(rs.getTimestamp("publish_time").toLocalDateTime());
            article.setUserid(rs.getInt("user_id"));
            article.setTpyeid(rs.getInt("type_id"));
            article.setContent(rs.getString("content"));
            articleVo = new ArticleVo();
            articleVo.setArticle(article);
            articleVo.setNickname(rs.getString("nickname"));
            articleVo.setAvatar(rs.getString("avatar"));

        }
       DbUtil.close(rs, pstmt, connection);
        return articleVo;
    }
}
