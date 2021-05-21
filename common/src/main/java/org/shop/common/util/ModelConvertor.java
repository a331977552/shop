package org.shop.common.util;

import org.springframework.beans.BeanUtils;

import java.util.function.Supplier;

public interface ModelConvertor<DAO,VO,ReturnVO> {

	default VO convertToVO(DAO dao, Supplier<? extends VO> ctor){
		VO vo = ctor.get();
		BeanUtils.copyProperties(dao,vo);
		return  vo;
	}

	default DAO convertToDAO(VO vo, Supplier<? extends DAO> ctor){
		DAO dao = ctor.get();
		BeanUtils.copyProperties(vo,dao);
		return  dao;
	}


	default ReturnVO convertToReturnVO(DAO dao, Supplier<? extends ReturnVO> ctor){
		ReturnVO vo = ctor.get();
		BeanUtils.copyProperties(dao,vo);
		return  vo;
	}


}
