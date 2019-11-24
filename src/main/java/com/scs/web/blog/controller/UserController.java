package com.scs.web.blog.controller;/*@ClassName UserController
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.listener.MySessionContext;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.Message;
import com.scs.web.blog.util.ResponseObject;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.interfaces.RSAKey;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/user", "/api/user/*"})
public class UserController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();

    /**
     * 根据请求地址，取得最后的内容，结合请求方法来决定分发到不同的方法
     *
     * @param uri
     * @return
     */
    private String getPatten(String uri) {
        int len = "/api".length();
        return uri.substring(len);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String uri = req.getRequestURI().trim();
       if("api/user/sign-in".equals(uri)){
           signIn(req,resp);
       }else if("api/user/sign-up".equals(uri)){
           signUp(req,resp);
       }
    }

    private void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    protected void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while((line = reader.readLine())!= null){
            stringBuilder.append(line);
        }
        logger.info("登陆用户信息"+stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(),UserDto.class);
        String inputCode = userDto.getCode().trim();

      //从客户端请求头里带来的token
        String sessionId = req.getHeader("Access-Token");
        System.out.println("客户端传来的JSESSIONID"+sessionId);
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionId);
        String correctCode =session.getAttribute("code").toString();
        System.out.println("正确的验证码"+correctCode);
     PrintWriter out = resp.getWriter();
     if(inputCode.equalsIgnoreCase(correctCode)){
         Result result = userService.signIn(userDto);
         out.print(gson.toJson(result));
     }else {
         Result result = Result.failure(ResultCode.USER_VERIFY_CODE_ERROR);
         out.print(gson.toJson(result));
     }
     out.close();
    }
    @Override
    public void init() throws ServletException {
        logger.info("UserController初始化");
    }
    private  void signUp(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while((line = reader.readLine())!=null){
          stringBuilder.append(line);
        }
        logger.info("注册用户信息"+stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(),UserDto.class);
        Map<String,Object> map = userService.signUp(userDto);
        String msg = (String)map.get("msg");
        ResponseObject ro;
        if(msg.equals("注册成功")){
            ro= ResponseObject.success(200,msg);
        }else {
            ro = ResponseObject.success(200, msg);
        }
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();

    }

    }

