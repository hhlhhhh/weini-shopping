package com.weini.service;

import com.weini.common.response.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ImageService {

    Result uploadImage(MultipartFile file, HttpServletRequest req);

    byte[] getImage(String url, HttpServletRequest req, HttpServletResponse rep);
}
