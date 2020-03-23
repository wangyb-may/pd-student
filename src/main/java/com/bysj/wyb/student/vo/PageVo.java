package com.bysj.wyb.student.vo;


import lombok.Data;

/**
 * @author wangyb
 */
@Data
public class PageVo {
    int pageNum;

    String uid;

    String keywords;

    Integer isShowHadUp;
}
