package com.bysj.wyb.student.service;


import com.bysj.wyb.common.result.HandleResult;
import com.bysj.wyb.common.result.PageResult;
import com.bysj.wyb.common.result.Result;
import com.bysj.wyb.student.entity.Student;
import com.bysj.wyb.student.mapper.HomeworkMapper;
import com.bysj.wyb.student.vo.HomeworkVo;

import com.bysj.wyb.student.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangyb
 */
@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Resource
    HomeworkMapper homeworkMapper;



    @Override
    public Result showStuHomeworkById(PageVo pageVo) {
        PageResult pr=new PageResult();
        HandleResult rs=new HandleResult();
        List<HomeworkVo> homeworkVos=homeworkMapper.personalHomework(pageVo.getUid());

        for(HomeworkVo h : homeworkVos){
            HomeworkVo littleHomework=homeworkMapper.showStatus(h.getHomeworkId(),pageVo.getUid());
            if(null!=littleHomework){
                h.setScore(littleHomework.getScore());
                h.setUpStatu(littleHomework.getUpStatu());
                h.setUpTime(littleHomework.getUpTime());
                if("1".equals(littleHomework.getUpStatu())){
                    h.setUpStatu("已提交");
                }else{
                    h.setUpStatu("未提交");
                    h.setUpTime("未提交");
                }
            }
        }
        if(pageVo.getIsShowHadUp()==1){
            homeworkVos=isShowUp(homeworkVos);
        }
        pr.pageStarter(pageVo.getPageNum(),5, Arrays.asList(homeworkVos.toArray()));
        return pr.pageRes(pr);

    }

    @Override
    public Result findHomeworkBySomething(PageVo pageVo) {
        PageResult pr=new PageResult();
        HandleResult rs=new HandleResult();
        List<HomeworkVo> homeworkVos=homeworkMapper.findHomeworkBySomething(pageVo.getUid(),pageVo.getKeywords());
        for(HomeworkVo h : homeworkVos){
            HomeworkVo littleHomework=homeworkMapper.showStatus(h.getHomeworkId(),pageVo.getUid());
            if(null!=littleHomework){
                h.setScore(littleHomework.getScore());
                h.setUpStatu(littleHomework.getUpStatu());
                h.setUpTime(littleHomework.getUpTime());
                if("0".equals(h.getUpStatu())){
                    h.setUpStatu("未提交");
                }else if("1".equals(h.getUpStatu())){
                    h.setUpStatu("已提交");
                }
                if(null==h.getUpTime()){
                    h.setUpTime("未提交");
                }
            }
        }
        if(pageVo.getIsShowHadUp()==1){
            homeworkVos=isShowUp(homeworkVos);
        }
        pr.pageStarter(pageVo.getPageNum(),5, Arrays.asList(homeworkVos.toArray()));
        return pr.pageRes(pr);
    }

    @Override
    public Result uplodHomework(MultipartFile file, Student student) {
        HandleResult hr=new HandleResult();

        return null;
    }


    public List<HomeworkVo> isShowUp(List<HomeworkVo> homeworkVos){
        for(int i=0;i<homeworkVos.size();i++){
            if(("已提交").equals(homeworkVos.get(i).getUpStatu())&&null!=homeworkVos.get(i).getUpStatu()){
                homeworkVos.remove(homeworkVos.get(i));
                i--;
            }
        }
        return homeworkVos;
    }

}
