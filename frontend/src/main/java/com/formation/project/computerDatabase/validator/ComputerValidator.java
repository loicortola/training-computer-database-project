package com.formation.project.computerDatabase.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.formation.project.computerDatabase.base.Computer;

@Component
public class ComputerValidator implements Validator {
		
		@Override
		public boolean supports(Class<?> clazz) {
			return Computer.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			
			Computer computer = (Computer) target;
			
			ValidationUtils.rejectIfEmpty(errors, "introduced", "introduced.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
			if(computer.getCompany() == null || computer.getCompany().equals("0"))
			{
				errors.rejectValue("company", "company.required");
			}			
		}
		
}
