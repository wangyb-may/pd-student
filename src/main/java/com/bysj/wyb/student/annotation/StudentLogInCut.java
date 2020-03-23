package com.bysj.wyb.student.annotation;

import com.bysj.wyb.common.result.HandleResult;
import com.bysj.wyb.common.result.Result;
import com.bysj.wyb.student.Feign.CommonFeign;
import com.bysj.wyb.student.entity.Student;
import com.bysj.wyb.student.mapper.StudentMapper;
import com.bysj.wyb.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyb
 */
@Aspect
@Component
@Slf4j
public class StudentLogInCut {

    boolean isInRedis=false;

    @Autowired
    RedisTemplate redisTemplate;

    @Resource
    StudentService studentService;

    @Resource
    CommonFeign commonFeign;


    @Pointcut("@annotation(com.bysj.wyb.student.annotation.StudentCut)")
    public void StudentLog(){

    }



    @Around("StudentLog()")
    public Object  methodAround(ProceedingJoinPoint point){
        HandleResult result=new HandleResult();
        String key="students";
        try {
            log.info("----------接到用户登录请求---------");
            log.info("----------开始查询Redis缓存---------");
            //使用redis对LIST集合的操作对象
            ListOperations<String,Student> operations = redisTemplate.opsForList();

            //检测学生用户集数据是否存在
            if(redisTemplate.hasKey(key)){
                //获取用户集中所有数据
                List<Student> stus=operations.range(key,0,-1);
                Object[] user=point.getArgs();
                Student temp=(Student) user[0];
                //检测数据中是否有用户登录信息并验证信息是否正确
                for(Student e : stus){
                    if(e.getPassword().equals(temp.getPassword())&&temp.getNickName().equals(e.getNickName())) {
                        isInRedis=true;
                        log.info("----------用户在缓存中存在---------");
                        log.info("----------开始验证用户登录---------");
                        commonFeign.logCounter(e.getUid());
                        return result.outResultWithData("0", "用户登陆成功！", e);
                    }
                    else if(e.getPassword()!=temp.getPassword()&&temp.getNickName().equals(e.getNickName())){
                        return result.outResultWithoutData("1","密码不正确！");
                    }
                }
                //用户集中无此用户信息，进行接口方法检测登录
                log.info("----------新用户登录---------");
                Object obj=point.proceed();
                Result rs=(Result)obj;
                //用户登录成功，将新用户存入redis
                if("0".equals(rs.getStatus())){
                    log.info("----------新用户存入用户集中---------");
                    Student ss=(Student)rs.getData();
                    redisTemplate.opsForList().leftPush(key, ss);
                }
                return obj;
            }else{
                isInRedis=false;
                log.info("----------用户集在缓存中不存在---------");
                Object obj=point.proceed();
                Result rs=(Result)obj;
                if(rs.getStatus().equals("0")){
                    log.info("----------创建用户集存入缓存中---------");
                    Student ss=(Student)rs.getData();
                    redisTemplate.opsForList().leftPush(key, ss);
                }
                return obj;
            }

        }catch (Throwable t){
            log.info("----------错误---------");
            log.error(t.getMessage(),t);
            return null;
        }
    }

}
