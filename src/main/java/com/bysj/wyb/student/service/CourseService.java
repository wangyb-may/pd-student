package com.bysj.wyb.student.service;

import com.bysj.wyb.student.result.Result;

/**
 * @author wangyb
 */
public interface CourseService {
    /**
     * 返回课程列表
     *
     * @return
     */
    Result findCourseList();

    /**
     * 搜索课程
     *
     * @param keywords
     * @return
     */
    Result findCourseListByKey(String keywords);

    /**
     * 选课
     *
     * @param courseId
     * @param userId
     * @return
     */
    Result chooseCourse(String courseId, String userId);

    /**
     * 返回已选列表
     *
     * @param uid
     * @return
     */
    Result findMyCourse(String uid);

    /**
     * 取消选课
     *
     * @param uid
     * @param courseId
     * @return
     */
    Result delMyCourse(String uid, String courseId);

    /**
     * 查询附件列表
     *
     * @param uid
     * @return
     */
    Result findAttachmentList(String uid);
}
