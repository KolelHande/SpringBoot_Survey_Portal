package com.company.portal.demo.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 @Target({FIELD}): Bu doğrulama açıklamasının yalnızca sınıf alanları için geçerli olduğunu belirtir. Yani, bu doğrulama açıklamasını sınıf veya yöntem seviyesinde kullanmak mümkün değildir.

 @Retention(RUNTIME): Bu doğrulama açıklamasının, programın çalışma zamanında da kullanılabilir olacağını belirtir. Bu, doğrulama açıklamasının derleme sırasında işlenmesi gerektiği anlamına gelir.

 @Constraint(validatedBy = QuestionTypeValidation.class): Bu doğrulama açıklamasının, doğrulama mantığını sağlayacak olan sınıfı belirtir. QuestionTypeValidation sınıfı, javax.validation.ConstraintValidator arayüzünü uygulayarak özel doğrulama mantığını sağlar.

 @Documented: Bu doğrulama açıklamasının JavaDoc dokümantasyonuna dahil edilmesini sağlar. Yani, bu doğrulama açıklamasını kullanan bir sınıfın JavaDoc dokümantasyonunda bu doğrulama açıklamasının açıklaması görüntülenebilir.
 */

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = QuestionTypeValidation.class)
@Documented
public @interface ValidQuestionType {
    String message() default "Invalid question type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
