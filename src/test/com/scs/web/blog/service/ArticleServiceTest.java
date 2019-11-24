package com.scs.web.blog.service;

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.ServiceFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArticleServiceTest {
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();

    @Test
    public void listArticle() {
        System.out.println(articleService.listArticle().size());

    }

    @Test
    public void getArticle() {
    }

    @Test
    public void getHotArticles() {
        System.out.println(articleService.getHotArticles());
    }
}