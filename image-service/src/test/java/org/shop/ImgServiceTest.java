package org.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shop.model.vo.ImageReturnVO;
import org.shop.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@SpringBootTest
public class ImgServiceTest {

	@Autowired
	ImageService service;

	@Test
	public void addFile(){
		try {
			final URL resource = ImgServiceTest.class.getClassLoader().getResource("test.png");
			final FileInputStream fileInputStream = new FileInputStream(resource.getFile());
			MockMultipartFile mockMultipartFile =new MockMultipartFile("TEST.png","TEST.png",null,fileInputStream );
			final ImageReturnVO imageReturnVO = service.addImg(mockMultipartFile);
			System.out.println(imageReturnVO);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	@Test
	public void getFile(){
		try {
			final InputStream imageReturnVO = service.findImgById("60a7c292e61a230e1087d6eb");
			final byte[] bytes = imageReturnVO.readAllBytes();
			System.out.println(bytes.length);
			Assertions.assertEquals(14372, bytes.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
