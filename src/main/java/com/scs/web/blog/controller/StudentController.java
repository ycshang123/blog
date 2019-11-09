package com.scs.web.blog.controller;
/*@ClassName StudentController
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/


import com.google.gson.Gson;
import com.scs.web.blog.entity.Student;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/student")
public class StudentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentService studentService = ServiceFactory.getStudentServiceInstance();
        List<Student> studentList = studentService.listStudent();
        Gson gson = new Gson();
        resp.setContentType("application/json;charst=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(studentList));
        out.close();
    }
}
