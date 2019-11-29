package com.scs.web.blog.dao;/*@ClassName TopicDao
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/25
 *@Version 1.0
 **/

import com.scs.web.blog.domain.Vo.TopicVo;
import com.scs.web.blog.entity.Topic;

import java.sql.SQLException;
import java.util.List;

public interface TopicDao {
    /*
    * 批量新增专题
    * */
    void batchInsert(List<Topic> topicList) throws SQLException;
    /*
    * 获取所有专题
    * */
    List<Topic> sellectAll() throws  SQLException;
    /*
    * 获取热门专题
    * */
    List<Topic> selectHotTopics() throws  SQLException;
    /*
    * 分页获取专题
    * */
    List<Topic> selectByPage(int currentPage,int count) throws  SQLException;
    /*
    * 根据id获取专题
    * */
    TopicVo getTopic(long id) throws  SQLException;
    /*
    * 模糊搜索专题
    * */
    List<Topic> selectByKeywords(String keywords) throws  SQLException;
}
