package com.bysj.wyb.student.controller;

import com.bysj.wyb.student.result.Result;
import com.bysj.wyb.student.service.CourseService;
import com.bysj.wyb.student.vo.PageVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wangyb
 */
@RequestMapping(value = "/stuCourse")
@RestController
public class CourseController {

    @Resource
    CourseService courseService;

    /**
     * 返回课程列表
     *
     * @param pageVo
     * @return
     */
    @RequestMapping(value = "/findList")
    public Result findCourseList(@RequestBody(required = false) PageVo pageVo) {
        if (null != pageVo) {
            return courseService.findCourseListByKey(pageVo.getKeywords());
        } else {
            return courseService.findCourseList();
        }
    }

    /**
     * 选择加入课程
     *
     * @param resBody
     * @return
     */
    @RequestMapping(value = "/chooseCourse")
    public Result chooseCourse(@RequestBody Map<String, String> resBody) {
        String courseId = resBody.get("courseId");
        String uid = resBody.get("uid");
        return courseService.chooseCourse(courseId, uid);
    }

    /**
     * 已选课程
     *
     * @param resBody
     * @return
     */
    @RequestMapping(value = "/myCourse")
    public Result myCourse(@RequestBody Map<String, String> resBody) {
        String uid = resBody.get("uid");
        return courseService.findMyCourse(uid);
    }

    /**
     * 取消选择课程
     *
     * @param resBody
     * @return
     */
    @RequestMapping(value = "/delMyCourse")
    public Result delMyCourse(@RequestBody Map<String, String> resBody) {
        String courseId = resBody.get("courseId");
        String uid = resBody.get("uid");
        return courseService.delMyCourse(uid, courseId);
    }

    /**
     * 查看课程附件信息
     *
     * @param resBody
     * @return
     */
    @RequestMapping(value = "/findAttachmentList")
    public Result findAttachmentList(@RequestBody Map<String, String> resBody) {
        return courseService.findAttachmentList(resBody.get("uid"));
    }
}
