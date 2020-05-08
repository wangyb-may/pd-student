package com.bysj.wyb.student.annotation;

import com.bysj.wyb.student.result.HandleResult;
import com.bysj.wyb.student.result.Result;
import com.bysj.wyb.student.vo.HomeworkVo;
import com.bysj.wyb.student.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangyb
 */
@Aspect
@Component
@Slf4j
public class HomeworkAspect {

    @Pointcut("@annotation(com.bysj.wyb.student.annotation.Homework)")
    public void HomeworkLimit(){

    }

    @Around("HomeworkLimit()")
    public Result LimitHomework(ProceedingJoinPoint point){
        HandleResult hr=new HandleResult();
        Object[] request=point.getArgs();
        PageVo data=(PageVo)request[0];
        boolean isShow=false;
        //是否显示已提交的作业(1为不显示
        if(1==data.getIsShowHadUp()){
            isShow=true;
        }
        try{
            Result rs=(Result)point.proceed();
            List<HomeworkVo> homeworkVos=(List<HomeworkVo>)rs.getData();
            if(isShow){
                for(int i=0;i<homeworkVos.size();i++){
                    if(("已提交").equals(homeworkVos.get(i).getUpStatu())&&null!=homeworkVos.get(i).getUpStatu()){
                        homeworkVos.remove(homeworkVos.get(i));
                        i--;
                    }
                }
            }
            return hr.outResultWithData("0","success",homeworkVos);
        }catch (Throwable t){
            log.info("----------错误---------");
            log.error(t.getMessage(),t);
            return hr.outResultWithoutData("1",t.getMessage());
        }

    }
}
