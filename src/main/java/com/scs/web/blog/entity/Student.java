package com.scs.web.blog.entity;/*@ClassName Student
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/6
 *@Version 1.0
 **/

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student {
    private Integer id;
    private String username;
    private String avatar;
    private LocalDateTime createTime;
    private String introduce;
}
