package com.scs.web.blog.controller;/*@ClassName AricleController
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/10
 *@Version 1.0
 **/

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.ArticleService;
import com.scs.web.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/api/article")
public class AricleController extends HttpServlet {
    ArticleService articleService = ServiceFactory.getArticleServiceInstance();
    private static Logger logger = LoggerFactory.getLogger(AricleController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Article> articleList = articleService.listArticle();
        ResponseObject ro = null;
    if(resp.getStatus() ==200){
        ro = ResponseObject.success(200,"成功",articleList);
    }else{
        ro = ResponseObject.error(resp.getStatus(),"失败");
    }
    PrintWriter out = resp.getWriter();
    Gson gson = new GsonBuilder().create();
    out.print(gson.toJson(ro));
    out.close();
    }

    @Override
    public void init() throws ServletException {
        logger.info("UserController初始化");
    }
}
