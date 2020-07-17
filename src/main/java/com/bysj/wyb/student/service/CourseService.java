package com.bysj.wyb.student.service;

import com.bysj.wyb.student.result.Result;

/**
 * @author wangyb
 */
public interface CourseService {
    Result findCourseList();

    Result findCourseListByKey(String keywords);

    /**
     * 选课
     * @param courseId
     * @param userId
     * @return
     */
    Result chooseCourse(String courseId,String userId);

    Result findMyCourse(String uid);

    Result delMyCourse(String uid,String courseId);

    /**
     * 查询附件列表
     * @param uid
     * @return
     */
    Result findAttachmentList(String uid);
}
