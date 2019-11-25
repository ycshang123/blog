package com.scs.web.blog.domain.Vo;
/*@ClassName ArticleVo
 *@Description:文章视图类，从文章、专题、用户表联查出前端需要展示的数据
 *@author yc_shang
 *@Date2019/11/20
 *@Version 1.0
 **/

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleVo {
    private Article article;
    private User author;
    private Topic topic;
}
