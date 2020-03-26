package com.bysj.wyb.student.service;

import com.bysj.wyb.common.result.Result;
import com.bysj.wyb.student.entity.Student;
import com.bysj.wyb.student.vo.HomeworkVo;
import com.bysj.wyb.student.vo.PageVo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

/**
 * @author wangyb
 */
public interface HomeworkService {
    /**
     * 按照学生id搜索该名学生的作业
     * @param uid,isShowUp
     * @return
     */
    Result showStuHomeworkById(PageVo pageVo);

    /**
     * 按照关键词搜索该名学生的作业
     * @param uid
     * @param keywords
     * @return
     */
    Result findHomeworkBySomething(PageVo pageVo);

    /**
     * 学生提交作业
     * @param file
     * @param student
     * @return
     */
    Result uplodHomework(@RequestParam("file") MultipartFile file, Student student);
}
