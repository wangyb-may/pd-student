package com.bysj.wyb.student.mapper;

import com.bysj.wyb.student.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {

    Student findStudentByUid(String uid);

    Student logIn(Student student);

    int signIn(Student student);

    int updatePersonlInformation(Student student);

    int updatePassword(String uid, String newPassword);

    int upCircleImage(String uid, String url);

}
