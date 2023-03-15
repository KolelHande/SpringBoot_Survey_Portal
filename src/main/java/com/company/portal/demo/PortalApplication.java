package com.company.portal.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class PortalApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }

    /* messageSource() methodu, mesajları yüklemek için bir ReloadableResourceBundleMessageSource nesnesi oluşturur. Bu nesne, messages.properties
    dosyasını bulmak için classpath'te arama yapar ve dosyadaki mesajlar UTF-8 karakter kodlaması kullanılarak yüklenir. Bu nesne daha sonra,
    LocalValidatorFactoryBean bean'ı tarafından kullanılmak üzere döndürülür.*/
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource= new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /* validator(MessageSource messageSource) methodu, veri doğrulama işlemlerinde kullanılacak bir LocalValidatorFactoryBean nesnesi oluşturur.
    Bu nesne, mesajları yüklemek için messageSource() methodundan döndürülen ReloadableResourceBundleMessageSource nesnesini kullanır.*/
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    /* localeResolver() methodu, kullanıcının tercih ettiği dili tutacak bir LocaleResolver nesnesi oluşturur. Bu örnekte, SessionLocaleResolver
    nesnesi oluşturulur ve varsayılan dil Locale.US olarak ayarlanır.*/
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return  localeResolver;
    }

    /* localeChangeInterceptor() methodu, kullanıcının tercih ettiği dili değiştirebileceği bir LocaleChangeInterceptor nesnesi oluşturur.
    Bu nesne, lang parametresine dayalı olarak kullanıcının tercih ettiği dili belirler. */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /* addInterceptors(InterceptorRegistry registry) methodu, localeChangeInterceptor() methodundan döndürülen LocaleChangeInterceptor nesnesini kaydeder.
    Bu, kullanıcının dil tercihini değiştirmek istediğinde Spring Framework'ün doğru şekilde ayarlanmasını sağlar. */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }

}
