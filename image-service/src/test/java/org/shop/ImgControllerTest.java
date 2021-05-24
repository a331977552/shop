package org.shop;

import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.model.vo.ImageReturnVO;
import org.shop.test.utils.RestTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImgControllerTest {

	@Autowired
	RestTestHelper helper;
	@LocalServerPort
	private int port;

	@Test
	public void addFile() {
		final ResponseEntity<Result<String>> resultResponseEntity = helper.loginWithDefault();
		final String token = resultResponseEntity.getBody().getResult();
		System.out.println(token);
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);


		var multipart = new LinkedMultiValueMap<>();
		multipart.add("img", file());

		final ResponseEntity<Result<ImageReturnVO>> post =
				helper.setUIPath("/api/img").setPort(port).builder().build().post(httpHeaders, multipart, new ParameterizedTypeReference<Result<ImageReturnVO>>() {
				});
		System.out.println(post);
		assertEquals(401, post.getStatusCodeValue());
		assertEquals(401, post.getBody().getCode());
		final ResponseEntity<Result<ImageReturnVO>> success =
				helper.setUIPath("/api/img").setPort(port).builder().withToken(token).build().
						post(httpHeaders, multipart, new ParameterizedTypeReference<Result<ImageReturnVO>>() {
				});
		final ImageReturnVO result = success.getBody().getResult();
		System.out.println(result);
		assertEquals("60ab1a914730a640ab45d183".length(), result.getId().length());
		assertEquals("60ab1a914730a640ab45d183".length(), result.getThumb().getId().length());
	}


	@Test
	public void retrieveFile() {
		final ResponseEntity<String> stringResponseEntity = helper.builder().setUipath("/api/img/60ab1a914730a640ab45d183").setPort(port).build().get(null, new ParameterizedTypeReference<String>() {
		}, null);
		assertEquals(200, stringResponseEntity.getStatusCodeValue());

	}

	private FileSystemResource file() {
		return new FileSystemResource(Path.of("src", "test", "resources", "test.png"));
	}

}
