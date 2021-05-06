package org.shop;

import lombok.val;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BeanConvertor {

	/**
	 * convert vo to dao and vice versa.
	 * @param source
	 * @param targetClass
	 * @param <T>
	 * @param <S>
	 * @return
	 */
	public static final <T, S> Optional<T> convert(Optional<S> source, Class<T> targetClass) {

		return  source.map(s->convert(s, targetClass));

	}


	/**
	 * convert vo to dao and vice versa.
	 * @param sourceList
	 * @param targetClass
	 * @param <T>
	 * @param <S>
	 * @return
	 */
	public static final <T, S> List<T> convert(List<S> sourceList, Class<T> targetClass) {
		return sourceList.stream().map(s->convert(s, targetClass)).collect(Collectors.toList());

	}
	private static <T,S> T convert(S s,Class<T> targetClass){
			try {
				T target = targetClass.getConstructor().newInstance();
				BeanUtils.copyProperties(s, target);
				return target;
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
				return null;

		}
	}
}