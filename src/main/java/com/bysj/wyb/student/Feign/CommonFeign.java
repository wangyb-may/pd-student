package com.bysj.wyb.student.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangyb
 */
@FeignClient(name = "pd-common")
public interface CommonFeign {

    @RequestMapping(value = "/system/logCounter")
    public void logCounter(@RequestParam String uid);
}
