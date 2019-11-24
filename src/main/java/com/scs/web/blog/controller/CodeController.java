package com.scs.web.blog.controller;/*@ClassName CodeController
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/20
 *@Version 1.0
 **/

import com.scs.web.blog.util.ImageUtil;
import com.scs.web.blog.util.StringUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/api/code")
public class CodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //生成验证码
        String code =StringUtil.getRandomString(4);
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        session.setAttribute("code",code);
        //生成图片
        BufferedImage img = ImageUtil.getImage(code,200,100);
        //
        resp.setContentType("image/jpg");
        //将图片通过输出流返回给客户端
        OutputStream out =resp.getOutputStream();
        ImageIO.write(img,"jpg",out);
        out.close();
    }
}
