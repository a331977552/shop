package org.shop.common.util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
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

	default List<ReturnVO> convertToReturnVO(List<DAO> daos, Class<ReturnVO> returnVOClass){
		List<ReturnVO> returnVOList = new ArrayList<>();
		for (DAO dao : daos) {
			try {
				final ReturnVO returnVO = returnVOClass.getConstructor().newInstance();
				BeanUtils.copyProperties(dao,returnVO);
				returnVOList.add(returnVO);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return  returnVOList;
	}


}
