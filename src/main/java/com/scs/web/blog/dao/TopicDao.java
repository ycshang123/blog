package com.scs.web.blog.dao;/*@ClassName TopicDao
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/25
 *@Version 1.0
 **/

import com.scs.web.blog.entity.Topic;

import java.sql.SQLException;
import java.util.List;

public interface TopicDao {
    /*
    * 批量新增专题
    * */
    void batchInsert(List<Topic> topicList) throws SQLException;
}
