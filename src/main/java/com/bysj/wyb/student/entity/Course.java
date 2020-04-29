package com.bysj.wyb.student.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "pd_course")
public class Course {

    private int id;

    @Column(name = "courseid")
    private String courseId;

    private String name;

    @Column(name = "coursecontext")
    private String courseContext;

    private String teacher;

    @Column(name = "classnumber")
    private String classNumber;

    @Column(name = "createtime")
    private String createTime;

    @Column(name = "isdelete")
    private int delete;
}
