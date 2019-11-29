package com.scs.web.blog.dao.Impl;/*@ClassName ArticleDaoImpl
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/10
 *@Version 1.0
 **/
import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.domain.Vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.util.BeanHandler;
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
        String sql = "INSERT INTO t_article(title,intro,cover,diamond,bookname,comments,likes,publish_time,user_id,type_id,content) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
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
                pstmt.setInt(10,article.getTpyeid());
                pstmt.setString(11, article.getContent());
                pstmt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        int[] result = pstmt.executeBatch();
        connection.commit();
        DbUtil.close(connection,pstmt);
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
        String sql = "SELECT a.*," +
                "b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.type_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "ORDER BY a.diamond DESC " +
                "LIMIT 10 ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        //调用封装方法，将结果解析成List
        List<ArticleVo> articleVoList = BeanHandler.converArticle(rs);
        DbUtil.close(connection,pstmt,rs);
        return articleVoList;
    }

    @Override
    public ArticleVo getArticle(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.type_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.id = ?  ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        ArticleVo articleVo = BeanHandler.converArticle(rs).get(0);
        rs.previous();
        DbUtil.close(connection,pstmt,rs);
        return articleVo;
    }

    @Override
    public List<ArticleVo> selectByUserId(long userId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.type_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.user_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, userId);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.converArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public List<ArticleVo> selectByTopicId(long typeId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.type_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.type_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, typeId);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos =  BeanHandler.converArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public List<ArticleVo> selectByPage(int currentPage, int count) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.type_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id  LIMIT ?,? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, (currentPage - 1) * count);
        pst.setInt(2, count);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos =  BeanHandler.converArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public List<ArticleVo> selectByKeywords(String keywords) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.type_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.title LIKE ?  OR a.intro LIKE ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, "%" + keywords + "%");
        pst.setString(2, "%" + keywords + "%");
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos =BeanHandler.converArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVos;

    }
}
