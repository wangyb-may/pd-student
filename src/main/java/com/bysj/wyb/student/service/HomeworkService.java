package com.bysj.wyb.student.service;

import com.bysj.wyb.common.result.Result;
import com.bysj.wyb.student.vo.HomeworkVo;
import com.bysj.wyb.student.vo.PageVo;


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
}
