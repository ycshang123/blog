package com.scs.web.blog.util;/*@ClassName ImageUtil
 *@Description:图片工具类
 *@author yc_shang
 *@Date2019/11/20
 *@Version 1.0
 **/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

//将字符串绘制成指定大小的图片
public class ImageUtil {
    public static BufferedImage getImage(String content,int width,int height){
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D)img.getGraphics();
        Color foreColor = Color.DARK_GRAY;
        g.setColor(new Color(26,160,52));
        g.drawRect(0,0,200,100);
        g.setBackground(new Color(0,0,0));
        Font font =new Font("楷体",Font.BOLD,30);
        g.setFont(font);
        g.drawString(content,width/3,height/3);
        return img;
    }

//    public static void main(String[] args)  throws IOException {
//        //生成验证码
//        String code =StringUtil.getRandomString(4);
//        //生成图片
//        BufferedImage img = ImageUtil.getImage(code,200,100);
//        //将img通过字节输出流输出到指定目录
//        File file = new File("D:/code.jpg");
//        ImageIO.write(img,"jpg",file);
//    }
}
