package com.scs.web.blog.util;/*@ClassName JsoupDemo
 *@Description:JSoup解析器
 *@author yc_shang
 *@Date2019/11/7
 *@Version 1.0
 **/

import com.scs.web.blog.entity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JsoupDemo {
    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);
    private static Logger log = LoggerFactory.getLogger(JSoupSpider.class);


    public static void main(String[] args) {
        {
            Document document = null;
            List<Article> articleList = new ArrayList<>(100);
            int j =0;
            for (int i = 1; i <= 180; i++) {
                try {
                    document = Jsoup.connect("https://book.douban.com/review/best?start=" + i).get();
                } catch (IOException e) {
                    log.error("豆瓣网站连接失败");
                }
                Elements divs = document.getElementsByClass("main review-item");
                divs.forEach(div -> {
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
                    article.setContent(body.child(1).child(0).text());
                    article.setLikes(Integer.valueOf(body.child(3).child(0).child(1).text()));
                    String comments = body.child(3).child(2).text();
                    int length = comments.length();
                    String number = comments.substring(0,length-2);
                    article.setComments(Integer.valueOf(number));
                    article.setDiamond(new Random().nextInt(100));
                    articleList.add(article);

                });
                j++;
                i = j*2*10;
            }
                System.out.println(articleList);
        }
    }
//    public static void main(String[] args) {
//        Document document = null;
//        List<Article> articleList = new ArrayList<>(100);
//        try {
//            document = Jsoup.connect("https://book.douban.com/review/best?start=40").get();
//        } catch (IOException e) {
//            log.error("豆瓣网站连接失败");
//        }
//        Elements divs = document.getElementsByClass("main review-item");
//        divs.forEach(div-> {
//            Element a = div.child(0);
//            Element header = div.child(1);
//            Element body = div.child(2);
//            String nickName = header.child(1).text();
//            System.out.println(nickName);
//            Elements img = a.children();
//            Elements contents = header.children();
//            Elements content = body.children();
//        });
//    }


}


