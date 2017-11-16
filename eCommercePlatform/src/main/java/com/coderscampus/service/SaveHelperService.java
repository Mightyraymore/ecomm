package com.coderscampus.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

public class SaveHelperService 
{
	// if I think about this method in a specific way, I'm subbing in "product" every time I see "T"
	// public static product save (JpaRepository<Product, Long>) repo, Product obj, Class<Product>clazz...
	public static <T> T save (JpaRepository<T, Long> repo, T obj, Class<T> clazz, String fieldName, String fieldValue) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		// passing in, for example: imageURL (as the fieldName)
	    // therefore the first letter needs to be uppercase to invoke the setter method 
		fieldName = StringUtils.capitalize(fieldName);
		
		Method method = clazz.getMethod("set" + fieldName, String.class);
		
		// ex: ("setImageUrl")
		method.invoke(obj, fieldValue);
		
			return repo.save(obj);
	}
}
