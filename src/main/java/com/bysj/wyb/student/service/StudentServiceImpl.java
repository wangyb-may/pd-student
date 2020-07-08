package com.bysj.wyb.student.service;

import com.bysj.wyb.student.result.HandleResult;
import com.bysj.wyb.student.result.IdWorker;
import com.bysj.wyb.student.result.Result;
import com.bysj.wyb.student.Feign.CommonFeign;
import com.bysj.wyb.student.entity.Student;
import com.bysj.wyb.student.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@Service
@Slf4j
public class StudentServiceImpl implements StudentService{

    @Resource
    StudentMapper studentMapper;

    @Resource
    CommonFeign commonFeign;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Result logIn(Student student) {
        HandleResult hr=new HandleResult();
        try{
            Student ss=studentMapper.logIn(student);
            //验证登录密码、用户名是否正确，返回验证信息及相应数据
            if(ss!=null){
                if(ss.getIsdelete()==1){
                    return hr.outResultWithoutData("1","该账号已经停用，请联系管理员！");
                }
                if(ss.getPassword().equals(student.getPassword())){
                    //数据库中增加用户登录信息
                    commonFeign.logCounter(ss.getUid());
                    return hr.outResultWithData("0","用户名密码验证通过",ss);
                }else{
                    return hr.outResultWithoutData("1","密码不正确");
                }
            }else{
                return hr.outResultWithoutData("1","用户名不存在！");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("0","服务异常，询问管理员是否有相同账号，如果有，请停用其中之一");
        }

    }

    @Override
    public Result SignIn(Student student) {
        HandleResult hr=new HandleResult();
        if(null==studentMapper.logIn(student)){
            IdWorker snow=new IdWorker(1,1,1);
            student.setUid(String.valueOf(snow.nextId()));
            if(1==studentMapper.signIn(student)){
                return hr.outResultWithData("0","注册成功！",student);
            }
        }else{
            return hr.outResultWithoutData("1","用户名已存在");
        }

        return null;
    }

    @Override
    public Result updatePersonalInformation(Student student) {
        HandleResult hr=new HandleResult();
        Student oldStu=studentMapper.findStudentByUid(student.getUid());
        if(1==studentMapper.updatePersonlInformation(student)){
            //更新redis
            String key="students";
            //更新redis集合中的登录信息集合
            if(redisTemplate.hasKey(key)){
                redisTemplate.opsForList().remove(key,0,oldStu);
                Student newStu=studentMapper.findStudentByUid(student.getUid());
                redisTemplate.opsForList().leftPush(key, newStu);
            }
            //返回前端结果
            return hr.outResultWithData("0","更新成功",student);
        }else{
            return hr.outResultWithoutData("1","更新失败");
        }
    }

    @Override
    public Result updatePassword(String uid,  String newPassword) {
        Student student=studentMapper.findStudentByUid(uid);
        HandleResult hr=new HandleResult();
        if(student.getPassword()!=newPassword){
            if(1==studentMapper.updatePassword(uid,newPassword)){

                //更新redis
                String key="students";
                //更新redis集合中的登录信息集合
                if(redisTemplate.hasKey(key)){
                    redisTemplate.opsForList().remove(key,0,student);
                    student.setPassword(newPassword);
                    redisTemplate.opsForList().leftPush(key, student);
                }
                //返回前端结果
                return hr.outResultWithData("0","修改成功!",student);
            }else{
                hr.outResultWithoutData("1","新旧密码不能一样！");
            }
        }

        return null;
    }

    @Override
    public Result upCircleImage(MultipartFile file,Student student) {
        HandleResult hr=new HandleResult();
        try{
            Result res=commonFeign.upload(file,"image/port/"+student.getUid()+"port");
            if(null!=res.getData()){
                studentMapper.upCircleImage(student.getUid(),res.getData().toString());
                return hr.outResultWithoutData("0","上传成功！");
            }else{
                return hr.outResultWithoutData("0","上传失败！");
            }
        }catch(Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("0","服务异常");
        }
    }

}
