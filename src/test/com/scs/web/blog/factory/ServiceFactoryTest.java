package com.scs.web.blog.factory;
import com.scs.web.blog.entity.Student;
import com.scs.web.blog.service.StudentService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ServiceFactoryTest {
    private StudentService studentService = ServiceFactory.getStudentServiceInstance();

    @Test
    public void getStudentServiceInstance() {
        List<Student> studentList = studentService.listStudent();
        studentList.forEach(System.out::println);
    }
}