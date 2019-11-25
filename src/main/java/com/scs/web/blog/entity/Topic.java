package com.scs.web.blog.entity;/*@ClassName Topic
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/20
 *@Version 1.0
 **/

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Topic {
    private  Long id;
    private Long adminId;
    private String topicName;
    private String logo;
    private String description;
    private Integer articles;
    private Integer follows;
    private LocalDateTime createTime;
}
