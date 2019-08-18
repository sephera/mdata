package com.github.sephera.mdata.autoconfigure;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@Import(MDataAutoConfiguration.class)
public @interface EnableMData {

    Class<?>[] basePackageClasses() default {};

    String prefix() default "spring";

}
