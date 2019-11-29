package com.scs.web.blog.dao.Impl;/*@ClassName TopicDaoImpl
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/25
 *@Version 1.0
 **/

import com.scs.web.blog.dao.TopicDao;
import com.scs.web.blog.domain.Vo.TopicVo;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.util.BeanHandler;
import com.scs.web.blog.util.DbUtil;
import com.scs.web.blog.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TopicDaoImpl implements TopicDao {
    private static Logger logger = LoggerFactory.getLogger(TopicDaoImpl.class);
    @Override
    public void batchInsert(List<Topic> topicList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_topic (admin_id,topic_name,logo,description,articles,follows,create_time) VALUES (?,?,?,?,?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        topicList.forEach(topic -> {
            try {
                pst.setLong(1, topic.getAdminId());
                pst.setString(2, topic.getTopicName());
                pst.setString(3, topic.getLogo());
                pst.setString(4, topic.getDescription());
                pst.setInt(5, topic.getArticles());
                pst.setInt(6, topic.getFollows());
                pst.setObject(7, topic.getCreateTime());
                pst.addBatch();
            } catch (SQLException e) {
                logger.error("批量加入专题数据产生异常");
            }
        });
        pst.executeBatch();
        connection.commit();
        DbUtil.close(connection, pst);
    }

    @Override
    public List<Topic> sellectAll() throws SQLException {
       Connection connection = DbUtil.getConnection();
       String sql = "SELECT * FROM t_topic ORDER BY id";
       PreparedStatement pst  = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<Topic> topicList = BeanHandler.convertTopic(rs);
        DbUtil.close(connection,pst,rs);
        return topicList;
    }

    @Override
    public List<Topic> selectHotTopics() throws SQLException {
      Connection connection = DbUtil.getConnection();
      String sql = "SELECT * FROM t_topic ORDER BY follows DESC LIMIT 8";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<Topic> topicList = BeanHandler.convertTopic(rs);
        DbUtil.close(connection,pst,rs);
        return topicList;
    }

    @Override
    public List<Topic> selectByPage(int currentPage, int count) throws SQLException {
       Connection connection = DbUtil.getConnection();
       String sql = "SELECT * FROM t_topic ORDER BY id LIMIT ?,?";
       PreparedStatement pst = connection.prepareStatement(sql);
       pst.setInt(1,(currentPage-1)*count);
       pst.setInt(2,count);
       ResultSet rs = pst.executeQuery();
       List<Topic> topicList = BeanHandler.convertTopic(rs);
       DbUtil.close(connection,pst,rs);
        return topicList;
    }

    @Override
    public TopicVo getTopic(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //查询专题详情，包括专题表信息，管理员简要信息，文章列表，关注人列表
        String sql = "SELECT a.*,b.id,b.nickname,b.avatar " +
                "FROM t_topic a " +
                "LEFT JOIN t_user b " +
                "ON a.admin_id = b.id " +
                "WHERE a.id = ?  ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        TopicVo topicVo = null;
        if (rs.next()) {
            topicVo = new TopicVo();
            //专题基本信息
            Topic topic = new Topic();
            topic.setId(rs.getLong("id"));
            topic.setAdminId(rs.getLong("admin_id"));
            topic.setTopicName(rs.getString("topic_name"));
            topic.setLogo(rs.getString("logo"));
            topic.setDescription(rs.getString("description"));
            topic.setArticles(rs.getInt("articles"));
            topic.setFollows(rs.getInt("follows"));
            topic.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            topicVo.setTopic(topic);

            //管理员基本信息
            User admin = new User();
            admin.setId(rs.getLong("admin_id"));
            admin.setNickname(rs.getString("nickname"));
            admin.setAvatar(rs.getString("avatar"));
            topicVo.setAdmin(admin);
        }
        DbUtil.close(connection, pst, rs);
        return topicVo;
    }

    @Override
    public List<Topic> selectByKeywords(String keywords) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_topic " +
                "WHERE topic_name LIKE ?  OR description LIKE ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, "%" + keywords + "%");
        pst.setString(2, "%" + keywords + "%");
        ResultSet rs = pst.executeQuery();
        List<Topic> topicList = BeanHandler.convertTopic(rs);
        DbUtil.close(connection, pst, rs);
        return topicList;
    }
}
