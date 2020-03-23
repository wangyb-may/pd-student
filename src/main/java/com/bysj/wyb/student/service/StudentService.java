package com.bysj.wyb.student.service;

import com.bysj.wyb.common.result.Result;
import com.bysj.wyb.student.entity.Student;

import javax.servlet.http.HttpServletRequest;

public interface StudentService {

    /**
     * 学生登录验证
     * @param student
     * @return
     */
    Result logIn(Student student);

    /**
     * 学生注册
     * @param student
     * @return
     */
    Result SignIn(Student student);

    /**
     * 学生修改自身信息
     * @param student
     * @return
     */
    Result updatePersonalInformation(Student student);

    /**
     * 学生修改密码
     * @param uid
     * @param newPassword
     * @return
     */
    Result updatePassword(String uid,String newPassword);

    /**
     * 修改论坛昵称
     * @param name
     * @param uid
     * @return
     */
    Result updateForumName(String name,String uid);
}
