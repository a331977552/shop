package org.shop.common.util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public interface ModelConvertor<DAO, AddVO,ReturnVO> {

	default AddVO convertToVO(DAO dao, Supplier<? extends AddVO> ctor){
		AddVO addVo = ctor.get();
		BeanUtils.copyProperties(dao, addVo);
		return addVo;
	}

	default DAO convertToDAO(AddVO addVo, Supplier<? extends DAO> ctor){
		DAO dao = ctor.get();
		BeanUtils.copyProperties(addVo,dao);
		return  dao;
	}


	default ReturnVO convertToReturnVO(DAO dao, Supplier<? extends ReturnVO> ctor){
		ReturnVO vo = ctor.get();
		if(dao==null)
			return null;
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
