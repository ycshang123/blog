package com.scs.web.blog.service;/*@ClassName TopicService
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/25
 *@Version 1.0
 **/

import com.scs.web.blog.util.Result;

public interface TopicService {
    /*
    * 获取热门专题
    * */
    Result getHotTopics();

    /*
    * 根据id获取专题详情
    * */
    Result getTopic(long id);
    /*
    * 模糊查询
    * */
    Result selectByKeywords(String keywords);

    /*
    * 分页查找
    * */
    Result selectByPage(int currentPage, int count);
}
