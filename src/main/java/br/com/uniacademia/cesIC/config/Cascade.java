package br.com.uniacademia.cesIC.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.uniacademia.cesIC.constant.CascadeType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Cascade {

    CascadeType value() default CascadeType.ALL;
}
