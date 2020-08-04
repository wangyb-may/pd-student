package com.bysj.wyb.student.mapper;

import com.bysj.wyb.student.entity.Attachment;
import com.bysj.wyb.student.entity.Course;
import com.bysj.wyb.student.entity.Homework;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangyb
 */
@Mapper
public interface CourseMapper {

    List<Course> findCourseList();

    List<Course> findCourseListByKey(String keyworkds);

    int chooseCourse(String courseId, String userId, String chooseTime);

    int addHomeworkStatus(String homeworkId, String userId);

    List<Homework> findCourseHomework(String courseId);

    List<Course> findPersonalCourse(String userId);

    List<Course> findMyCourse(String uid);

    int delMyCourse(String uid, String courseId);

    int delMyCourseHomeworkStatu(String uid, String courseId);

    List<Attachment> findAttachmentByUid(String uid);
}
