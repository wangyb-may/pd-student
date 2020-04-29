package com.bysj.wyb.student.service;

import com.bysj.wyb.common.result.Result;

/**
 * @author wangyb
 */
public interface CourseService {
    Result findCourseList();

    Result findCourseListByKey(String keywords);

    Result chooseCourse(String courseId,String userId);

    Result findMyCourse(String uid);

    Result delMyCourse(String uid,String courseId);
}
