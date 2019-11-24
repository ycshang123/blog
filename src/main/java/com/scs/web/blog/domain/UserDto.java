package com.scs.web.blog.domain;/*@ClassName UserDto
 *@Description:/用户传输对象
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import lombok.Data;

@Data
public class UserDto {
    private  String mobile;
    private  String password;
    private String code;
}
