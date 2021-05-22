package org.shop.service.impl;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.shop.common.util.ModelConvertor;
import org.shop.exception.ImgException;
import org.shop.mapper.ImageDAOMapper;
import org.shop.model.dao.ImageDAO;
import org.shop.model.vo.ImageAddVO;
import org.shop.model.vo.ImageReturnVO;
import org.shop.service.ImageService;
import org.shop.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class ImageServiceImpl implements ImageService, ModelConvertor<ImageDAO,ImageAddVO,ImageReturnVO> {


	@Autowired
	GridFsTemplate client2;

	private ImageDAOMapper mapper;

	@Value("${img.thumb.width}")
	private int thumbnailW;

	@Value("${img.thumb.height}")
	private int thumbnailH;

	public ImageServiceImpl(ImageDAOMapper mapper) {
		this.mapper = mapper;
	}


	@Override
	@Transactional
	public ImageReturnVO addImg(MultipartFile file) {
		try {
			final String originalFilename = file.getOriginalFilename();
			final ImageReturnVO imageReturnVO = new ImageReturnVO();
			final ObjectId originalFileId = client2.store(file.getInputStream(), originalFilename);
			byte[] thumbnailFileName = ImgUtil.createThumbnail(file.getInputStream(), originalFilename,thumbnailW, thumbnailH, false);
			final ObjectId thumbId = client2.store(new ByteArrayInputStream(thumbnailFileName), ImgUtil.DEFAULT_PREVFIX + originalFilename);
			imageReturnVO.setId(originalFileId.toString());
			imageReturnVO.setThumb(new ImageReturnVO.Thumb(thumbId.toHexString()));
			return imageReturnVO;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ImgException("error occurred when adding img", e);
		}
	}

	@Override
	public InputStream findImgById(String imgId) throws IOException {
		final GridFSFile id = client2.findOne(new Query(where("_id").is(imgId)));
		if(id==null)
			throw new ImgException("invalid id: "+ imgId);
		final GridFsResource resource = client2.getResource(id);
		return resource.getInputStream();
	}

}
