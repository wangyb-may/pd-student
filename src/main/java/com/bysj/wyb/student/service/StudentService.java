package com.bysj.wyb.student.service;

import com.bysj.wyb.student.result.Result;
import com.bysj.wyb.student.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangyb
 */
public interface StudentService {

    /**
     * 学生登录验证
     *
     * @param student
     * @return
     */
    Result logIn(Student student);

    /**
     * 学生注册
     *
     * @param student
     * @return
     */
    Result SignIn(Student student);

    /**
     * 学生修改自身信息
     *
     * @param student
     * @return
     */
    Result updatePersonalInformation(Student student);

    /**
     * 学生修改密码
     *
     * @param uid
     * @param newPassword
     * @return
     */
    Result updatePassword(String uid, String newPassword);

    /**
     * 上传头像
     *
     * @param file
     * @param student
     * @return
     */
    Result upCircleImage(MultipartFile file, Student student);

}
