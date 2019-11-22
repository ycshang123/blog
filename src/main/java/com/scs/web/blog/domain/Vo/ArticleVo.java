package com.scs.web.blog.domain.Vo;
/*@ClassName ArticleVo
 *@Description:文章视图类，从文章、专题、用户表联查出前端需要展示的数据
 *@author yc_shang
 *@Date2019/11/20
 *@Version 1.0
 **/

import com.scs.web.blog.entity.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleVo {
    private Article article;
    private long id;
    private String title;
    private String intro;
    private String cover;
    private Integer diamond;
    private String bookname;
    private Integer comments;
    private Integer likes;
    private LocalDateTime publishtime;
    private int userid;
    private int tpyeid;
    private String content;
    private String nickname;
    private String avatar;
//    private String topicName;
//    private String logo;
}
