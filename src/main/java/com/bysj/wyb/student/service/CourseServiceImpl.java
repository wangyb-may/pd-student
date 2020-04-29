package com.bysj.wyb.student.service;

import com.bysj.wyb.common.result.HandleResult;
import com.bysj.wyb.common.result.Result;
import com.bysj.wyb.student.entity.Course;
import com.bysj.wyb.student.entity.Homework;
import com.bysj.wyb.student.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangyb
 */
@Service
public class CourseServiceImpl implements CourseService{

    @Resource
    CourseMapper courseMapper;


    @Override
    public Result findCourseList() {
        HandleResult hr=new HandleResult();
        List<Course> courseList=courseMapper.findCourseList();
        if(!courseList.isEmpty()){
            for(Course temp : courseList){
                String[] times=temp.getCreateTime().split(" ");
                temp.setCreateTime(times[0]);
            }
            return hr.outResultWithData("0","返回列表成功",courseList);
        }else{
            return hr.outResultWithoutData("1","查询列表失败");
        }

    }

    @Override
    public Result findCourseListByKey(String keywords) {
        HandleResult hr=new HandleResult();
        List<Course> courseList=courseMapper.findCourseListByKey(keywords);
        if(!courseList.isEmpty()){
            for(Course temp : courseList){
                String[] times=temp.getCreateTime().split(" ");
                temp.setCreateTime(times[0]);
            }
            return hr.outResultWithData("0","返回列表成功",courseList);
        }else{
            return hr.outResultWithoutData("1","无此课程或老师的相关课程信息");
        }
    }

    @Override
    public Result chooseCourse(String courseId, String userId) {
        HandleResult hr = new HandleResult();
        try {
            List<Course> courseList=courseMapper.findPersonalCourse(userId);
            for(Course e : courseList){
                if(e.getCourseId().equals(courseId)){
                    return hr.outResultWithoutData("0","你已经添加过该课程！");
                }
            }
            courseMapper.chooseCourse(courseId,userId,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            List<Homework> homeworkList=courseMapper.findCourseHomework(courseId);
            if(homeworkList.isEmpty()) {
                return hr.outResultWithoutData("0","添加课程成功");
            }else{
                for(Homework temp : homeworkList){
                    courseMapper.addHomeworkStatus(temp.getId(),userId);
                }
                return hr.outResultWithoutData("0","添加课程成功!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return hr.outResultWithoutData("1","服务器异常");
        }

    }

    @Override
    public Result findMyCourse(String uid) {
        HandleResult hr=new HandleResult();
        try {
            List<Course> courseList=courseMapper.findMyCourse(uid);
            return hr.outResultWithData("0","查询成功",courseList);
        }catch (Exception e){
            return hr.outResultWithoutData("1",e.getMessage());
        }
    }

    @Override
    public Result delMyCourse(String uid, String courseId) {
        HandleResult hr=new HandleResult();
        try {
            courseMapper.delMyCourse(uid,courseId);
            courseMapper.delMyCourseHomeworkStatu(uid,courseId);
            return hr.outResultWithoutData("0","删除成功");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return hr.outResultWithoutData("1","服务器异常");
        }
    }

}
