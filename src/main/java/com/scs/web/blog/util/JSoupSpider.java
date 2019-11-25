package com.scs.web.blog.util;

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.entity.User;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author mq_xu
 * @ClassName JSoupSpider
 * @Description JSoup实现的一个爬虫工具
 * @Date 9:13 2019/11/7
 * @Version 1.0
 **/
public class JSoupSpider {
    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);
    private static Logger log = LoggerFactory.getLogger(JSoupSpider.class);
    private static final int PAGE_COUNT = 1;

    public static List<Article> getArticle(){
        Document document = null;
        List<Article> articleList = new ArrayList<>(100);
        int i,j =0;
        for(  i = 0 ;i<=180 ;i++){
            try {
                document = Jsoup.connect("https://book.douban.com/review/best?start="+i).get();
            } catch (IOException e) {
               log.error("豆瓣网站连接失败");
            }
            Elements divs = document.getElementsByClass("main review-item");
            divs.forEach(div ->{
                Element a = div.child(0);
                Element header = div.child(1);
                Element body = div.child(2);
                Article article = new Article();
                article.setCover(a.child(0).attr("src"));
                article.setBookname(header.child(1).text());
                if(header.children().size() == 4){
                    article.setPublishtime(Timestamp.valueOf(header.child(3).text()).toLocalDateTime());
                }else {
                    article.setPublishtime(Timestamp.valueOf(header.child(2).text()).toLocalDateTime());
                }
                article.setTitle(body.child(0).text());
                String articleUrl = body.child(0).child(0).attr("href");
                Document document1 = null;
                try {
                    document1 = Jsoup.connect(articleUrl).get();
                } catch (IOException e) {
                    logger.error("连接失败");
                }
                Element articleElement = document1.getElementById("link-report").child(0);
                System.out.println(articleElement.html());
                article.setContent(articleElement.html());
                article.setIntro(body.child(1).child(0).text());
                article.setLikes(Integer.valueOf(body.child(3).child(0).child(1).text()));
                String comments = body.child(3).child(2).text();
                int length = comments.length();
                String number = comments.substring(0,length-2);
                article.setComments(Integer.valueOf(number));
                article.setDiamond(new Random().nextInt(100));
                article.setUserid(DataUtil.getUserId());
                article.setTpyeid(DataUtil.getTopicId());
                articleList.add(article);
            });
            j++;
            i = 20*j;
        }
            return  articleList;
    }

    public static List<User> getUsers() {
        Document document = null;
        List<User> userList = new ArrayList<>(100);
        for (int i = 1; i <= 3; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("用户信息网站连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                User user = new User();
                user.setMobile(DataUtil.getMobile());
                user.setPassword(DataUtil.getPassword());
                user.setGender(DataUtil.getGender());
                user.setAvatar("https:" + linkChildren.get(0).attr("src"));
                user.setNickname(linkChildren.get(1).text());
                user.setIntroduction(linkChildren.get(2).text());
                user.setBirthday(DataUtil.getBirthday());
                user.setCreateTime(DataUtil.getCreateTime());
                user.setFollows(DataUtil.getFollows());
                userList.add(user);
            });
        }
        return userList;
    }
    /*爬取简书网的专题*/
    public static List<Topic> getTopics() {
        List<Topic> topicList = new ArrayList<>(100);
        Connection connection;
        Document document = null;
        for (int i = 1; i <= PAGE_COUNT; i++) {
            try {
                //分析页面得到url和惨
                connection = Jsoup.connect("https://www.jianshu.com/recommendations/collections?order_by=hot&page=" + i);
                //通过chrome开发者工具查看该请求必须添加请求头
                connection.header("X-PJAX", "true");
                document = connection.get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            assert document != null;
            Elements list = document.select(".collection-wrap");
            list.forEach(item -> {
                Elements elements = item.children();
                Topic topic = new Topic();
                Element link = elements.select("a").get(0);
                Element logo = link.child(0);
                Element name = link.child(1);
                Element description = link.child(2);
                Element articles = elements.select(".count > a").get(0);
                Element follows = elements.select(".count > a").get(0);
                topic.setAdminId(1L);
                topic.setTopicName(name.text());
                topic.setLogo(logo.attr("src"));
                topic.setDescription(description.text());
                String[] str = StringUtil.getDigital(articles.text());
                topic.setArticles(Integer.parseInt(str[0]));
                str = StringUtil.getDigital(follows.text());
                topic.setFollows(Integer.parseInt(str[0]));
                topic.setCreateTime(DataUtil.getCreateTime());
                topicList.add(topic);
            });
        }
        return topicList;
    }


}
