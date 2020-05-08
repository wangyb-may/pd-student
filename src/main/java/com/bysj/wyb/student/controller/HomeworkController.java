package com.bysj.wyb.student.controller;

import com.bysj.wyb.student.result.Result;
import com.bysj.wyb.student.service.HomeworkService;
import com.bysj.wyb.student.vo.PageVo;
import com.bysj.wyb.student.vo.UpDataVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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

    @RequestMapping(value = "/uploadHomework",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result uploadHomework(@RequestParam("file") MultipartFile file, UpDataVo upDataVo){
        System.out.println(file.getOriginalFilename());
        return homeworkService.uplodHomework(file,upDataVo.getHomeworkId(),upDataVo.getUid());
    }
}
