package com.company.portal.demo.util.validation;

import com.company.portal.demo.enums.QuestionTypeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuestionTypeValidation implements ConstraintValidator<ValidQuestionType, QuestionTypeEnum>{
    @Override
    public boolean isValid(QuestionTypeEnum value, ConstraintValidatorContext context) {
        return value != null;
    }
}
