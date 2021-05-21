package org.shop;

import org.junit.jupiter.api.Test;
import org.shop.model.vo.ImageReturnVO;
import org.shop.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

@SpringBootTest
public class ImgTest {

	@Autowired
	ImageService service;

	@Test
	public void addFile(){
		try {
			final URL resource = ImgTest.class.getClassLoader().getResource("test.png");
			System.out.println(resource.getFile());
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
//		try {
//
//			final ImageReturnVO imageReturnVO = service.addImg(null);
//
//			System.out.println(imageReturnVO);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}


	}
}
