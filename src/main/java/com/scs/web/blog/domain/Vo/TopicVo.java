package com.scs.web.blog.domain.Vo;/*@ClassName TopicVo
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/28
 *@Version 1.0
 **/

import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.entity.User;
import lombok.Data;

import java.util.List;
@Data
public class TopicVo {
    private Topic topic;
    private User admin;
    private List<ArticleVo> articleList;
    private List<User> followList;

}
