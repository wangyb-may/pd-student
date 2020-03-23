package com.bysj.wyb.student.mapper;

import com.bysj.wyb.student.vo.HomeworkVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangyb
 */
@Mapper
public interface HomeworkMapper {



    List<HomeworkVo> personalHomework(String uid);

    HomeworkVo showStatus(String homeworkId, String uid);

    List<HomeworkVo> findHomeworkBySomething(String uid, String keywords);


}
