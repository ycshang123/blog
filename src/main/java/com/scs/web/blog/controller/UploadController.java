package com.scs.web.blog.controller;
/*@ClassName UploadController
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/21
 *@Version 1.0
 **/
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@MultipartConfig
@WebServlet(urlPatterns = "/upload")
public class UploadController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //生成文件夹的名字
        LocalDate date = LocalDate.now();
        System.out.println(date);
        Collection<Part> parts =req.getParts();
        //设置最大上传文件的最大值
        long maxFilesize = 1024*1024*8;
        for(Part part :parts){
            long  filesize = part.getSize();
            System.out.println(filesize);
            if(filesize>maxFilesize){
                req.setAttribute("msg","文件过大，上传失败");
            }else if(filesize ==0){
                req.setAttribute("msg","请点击上方框框，选择上传文件");
            }else {
                //客户端文件夹的名称
                String name = part.getSubmittedFileName();
                System.out.println(name);
                //取文件的后缀
                int index = name.lastIndexOf(".");
                String suffix = name.substring(index);
                //UUID和文件后缀名相连接
                String filename = UUID.randomUUID().toString().replace("-", "") + suffix;
                String path = req.getSession().getServletContext().getRealPath("") + date;
                System.out.println(path);
                //创建新的文件夹，以Path命名
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                part.write(file.getPath() + "/" + filename);
                req.setAttribute("msg", "上传成功");
                req.setAttribute("url", "http://localhost:8080/" + date + "/" + filename);
                System.out.println(file.getName());
                System.out.println(file.getAbsolutePath() + "/" + filename);
            }
        }
        req.getRequestDispatcher("/upload.jsp").forward(req, resp);
        }

}
