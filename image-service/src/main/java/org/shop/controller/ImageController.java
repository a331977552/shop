package org.shop.controller;

import org.shop.common.Result;
import org.shop.exception.ImgException;
import org.shop.model.vo.ImageReturnVO;
import org.shop.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/img")
public class ImageController {

	final ImageService service;

	public ImageController(ImageService service) {
		this.service = service;
	}

	@PostMapping
	public Result<ImageReturnVO> addImg(@RequestBody MultipartFile file){
		final ImageReturnVO imageReturnVO = service.addImg(file);
		return Result.of(imageReturnVO);
	}

	@GetMapping(value = "/{id}")
	public void getImgByID(@PathVariable("id") String id, HttpServletResponse httpResponse){
		final InputStream inputStream;
		try {
			inputStream = service.findImgById(id);
			final ServletOutputStream outputStream = httpResponse.getOutputStream();
			httpResponse.setContentType(MediaType.IMAGE_PNG_VALUE);
			inputStream.transferTo(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ImgException("获取图片失败",e);
		}
	}

}
