package com.th.ox.cleaver.activiti.util.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.th.ox.cleaver.activiti.extension.exception.MyException;

public class ValidatorUtils {
	private static Validator validator;
	static {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * 校验对象
	 * 
	 * @param object
	 *            待校验对象
	 * @param groups
	 *            待校验的组
	 * @throws MyException
	 *             校验不通过，则报BusinessException异常
	 */
	public static void validateEntity(Object object, Class<?>... groups) throws MyException {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			ConstraintViolation<Object> constraint = (ConstraintViolation<Object>) constraintViolations.iterator().next();
			throw new MyException(constraint.getMessage());
		}
	}
}
