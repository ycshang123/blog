package com.scs.web.blog.util;/*@ClassName JsoupDemo
 *@Description:JSoup解析器
 *@author yc_shang
 *@Date2019/11/7
 *@Version 1.0
 **/

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupDemo {
    public static void main(String[] args) throws Exception{
        //声明文档变量
        Document document;
        //通过JSoup连接目标界面
        document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users").get();
        //选取所有class为col-xs-8的元素集合
        Elements divs = document.getElementsByClass("col-xs-8");
        //对divs遍历
        divs.forEach(div->{
            //取div的孩子节点集合
            Element wrapDiv = div.child(0);
//            System.out.println(wrapDiv.html());
            Element link = wrapDiv.child(0);
            Elements linkChild = link.children();
            System.out.println("https"+linkChild.get(0).attr("src"));
            System.out.println(linkChild.get(1).text());
            System.out.println(linkChild.get(2).text());
//            Element img = link.child(0);
//            System.out.println("http"+img.attr("src"));
        });
    }

}
