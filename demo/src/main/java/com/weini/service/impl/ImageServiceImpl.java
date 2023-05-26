package com.weini.service.impl;

import cn.hutool.core.map.MapUtil;
import com.weini.common.response.Result;
import com.weini.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


@Service
public class ImageServiceImpl implements ImageService {

    @Value("${weini.image.url}")
    String imageUrl;


    @Override
    public Result uploadImage(MultipartFile file, HttpServletRequest req) {
        {
            String suf = "";
            try{
                suf = Objects.requireNonNull(file.getOriginalFilename()).split("/.")[1];
            }catch (Exception e){
                e.printStackTrace();
                return Result.fail("图片上传失败："+e.getMessage());
            }

            String url = UUID.randomUUID().toString();
            File newFile=new File(imageUrl+"/"+ url+"."+suf);
            try {
                if(!newFile.createNewFile()){
                    return Result.fail("图片上传失败");
                }
                file.transferTo(newFile);
            } catch (IOException e) {
                return Result.fail("图片上传失败："+e.getMessage());
            }
            return Result.succ(MapUtil.builder()        //返回图片的唯一标识
                    .put("url",url)
                    .map());
        }
    }

    @Override
    public byte[] getImage(String url, HttpServletRequest req, HttpServletResponse rep) {

        File file=new File(imageUrl+"/"+url);
        if(file.exists()){
            try {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] bytes = new byte[inputStream.available()];

                inputStream.read(bytes, 0, inputStream.available());

                return bytes;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
