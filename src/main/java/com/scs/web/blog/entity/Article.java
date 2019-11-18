package com.scs.web.blog.entity;/*@ClassName Article
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Article {
    private long id;
    private String title;
    private String intro;
    private String cover;
    private Integer diamond;
    private String nickname;
    private Integer comments;
    private Integer likes;
    private LocalDateTime publishtime;
    private int userid;
    private int tpyeid;
    private String content;

}
