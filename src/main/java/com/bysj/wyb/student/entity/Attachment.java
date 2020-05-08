package com.bysj.wyb.student.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author admin
 */
@Data
@Table(name = "pd_attachment")
public class Attachment {

    private String id;

    private String name;

    @Column(name = "uptime")
    private String upTime;

    @Column(name = "upurl")
    private String upUrl;

    private String uid;

    @Column(name = "courseid")
    private String courseId;

    @Column(name = "coursename")
    private String courseName;
}
