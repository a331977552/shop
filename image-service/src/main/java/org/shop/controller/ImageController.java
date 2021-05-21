package org.shop.controller;

import org.shop.common.Result;
import org.shop.exception.ImgException;
import org.shop.model.vo.ImageReturnVO;
import org.shop.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
	public Result<ImageReturnVO> addImg(@RequestParam("img") MultipartFile file){

		final ImageReturnVO imageReturnVO = service.addImg(file);
		return Result.of(imageReturnVO);
	}

	@GetMapping("/{id}")
	public InputStream getImgByID(@PathVariable("id") String id){
		final InputStream inputStream;
		try {
			inputStream = service.findImgById(id);
			return inputStream;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ImgException("获取图片失败",e);
		}
	}


}
