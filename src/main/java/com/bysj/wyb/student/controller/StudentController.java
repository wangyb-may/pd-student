package com.bysj.wyb.student.controller;

import com.bysj.wyb.student.result.HandleResult;
import com.bysj.wyb.student.result.Result;
import com.bysj.wyb.student.annotation.StudentCut;
import com.bysj.wyb.student.entity.Student;
import com.bysj.wyb.student.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


/**
 * @author wangyb
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/student")
public class StudentController {

    @Resource
    StudentService studentService;

    /**
     * 学生登录
     * @param student
     * @return
     */
    @StudentCut
    @RequestMapping(value = "/login")
    public Result StuLogIn(@RequestBody Student student){
        return studentService.logIn(student);
    }

    @RequestMapping(value = "/signIn")
    public Result StuSignIn(@RequestBody Student student){
        return studentService.SignIn(student);
    }

    @RequestMapping(value = "/update")
    public Result UpdatePersonal(@RequestBody Student student){
        return studentService.updatePersonalInformation(student);
    }

    @GetMapping(value = "/updatePassword")
    public Result UpdatePassword(@RequestParam String uid, @RequestParam String newPassword){
        return studentService.updatePassword(uid,newPassword);
    }

    //上传头像
    @RequestMapping(value = "/updatePort")
    public Result UpdatePort(@RequestParam("file") MultipartFile file, Student student){
        return studentService.upCircleImage(file,student);
    }


}
