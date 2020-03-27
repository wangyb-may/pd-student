package com.bysj.wyb.student.Feign;

import com.bysj.wyb.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangyb
 */
@FeignClient(name = "pd-common")
public interface CommonFeign {

    @RequestMapping(value = "/system/logCounter")
    public void logCounter(@RequestParam String uid);

    @RequestMapping(value = "/system/upload")
    public Result upload(@RequestParam("file") MultipartFile file, @RequestParam String uploadCatalogAndName);
}
