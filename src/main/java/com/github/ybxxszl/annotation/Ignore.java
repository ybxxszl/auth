package com.github.ybxxszl.annotation;

import java.lang.annotation.*;

/**
 * @author wjy
 * @desc 忽略验证
 * @date 2019/10/23
 */
@Documented
@Inherited
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface Ignore {

}
