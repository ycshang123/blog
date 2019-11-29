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
    public static BufferedImage getImage(int width, int height, String content) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) img.getGraphics();
        graphics.setColor(new Color(68, 134, 49));
        graphics.fillRect(0, 0, width, height);
        graphics.setPaint(new Color(60, 63, 65));
        Font font = new Font("微软雅黑", Font.BOLD, 40);
        graphics.setFont(font);
        graphics.drawString(content, width / 3, height / 2);
        graphics.rotate(1.5);
        return img;
    }

}
