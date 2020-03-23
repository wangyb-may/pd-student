package com.bysj.wyb.student.controller;

import com.bysj.wyb.common.result.Result;
import com.bysj.wyb.student.annotation.Homework;
import com.bysj.wyb.student.service.HomeworkService;
import com.bysj.wyb.student.vo.HomeworkVo;
import com.bysj.wyb.student.vo.PageVo;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangyb
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/student/homework")
public class HomeworkController {

    @Resource
    HomeworkService homeworkService;

    @RequestMapping(value = "/List")
    public Result findHomeworkByPersonalId(@RequestBody PageVo pageVo){
        //PageHelper.startPage(pageVo.getPageNum(),5);
        return homeworkService.showStuHomeworkById(pageVo);
    }

    @RequestMapping(value = "/findByKeyword")
    public Result findHomeworkBySomething(@RequestBody PageVo pageVo){
        //PageHelper.startPage(pageVo.getPageNum(),5);
        return homeworkService.findHomeworkBySomething(pageVo);
    }

}
