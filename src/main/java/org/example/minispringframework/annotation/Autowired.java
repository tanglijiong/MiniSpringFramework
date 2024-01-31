package org.example.minispringframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 仅适用于字段
@Retention(RetentionPolicy.RUNTIME) // 运行时保留
public @interface Autowired {
}
