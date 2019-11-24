package com.scs.web.blog.domain.Vo;

import com.scs.web.blog.entity.User;
import lombok.Data;

import java.util.List;

/*@ClassName UserVo
 *@Description:用户视图类，包括用户自身的信息，发表的文章、关注的专题、所有的粉丝
 *@author yc_shang
 *@Date2019/11/24
 *@Version 1.0
 **/
@Data
public class UserVo {
    private User user;
    private List<ArticleVo> articleVoList;
    private List<User> fansList;
}
