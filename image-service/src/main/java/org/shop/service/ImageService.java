package org.shop.service;

import org.shop.model.vo.ImageReturnVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    ImageReturnVO addImg(MultipartFile img);
    InputStream findImgById(String imgId) throws IOException;
}