package com.weini.controller;

import com.weini.common.response.Result;
import com.weini.service.ImageService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 图片上传阅览功能
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @RequiresAuthentication
    @PostMapping("/upload")
    public Result uploadImage(MultipartFile file, HttpServletRequest req){
        return imageService.uploadImage(file, req);
    }


    @ResponseBody
    @GetMapping(value = "/{url}",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})     //只允许jepg，png图片
    public byte[] getImage(@PathVariable String url, HttpServletRequest req, HttpServletResponse rep){
        return imageService.getImage(url, req, rep);
    }
}
