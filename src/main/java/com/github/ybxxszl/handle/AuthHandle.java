package com.github.ybxxszl.handle;

import com.github.ybxxszl.bean.AuthBean;
import com.github.ybxxszl.util.ClassUtil;
import com.github.ybxxszl.util.JacksonUtil;
import com.github.ybxxszl.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author wjy
 * @desc 授权处理
 * @date 2019/10/17
 */
@Component
public class AuthHandle implements ApplicationRunner {

    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private ClassUtil classUtil;
    @Autowired
    private JacksonUtil jacksonUtil;

    public void run(ApplicationArguments args) throws Exception {

        String authPackageName = propertiesUtil.getProperty("auth.package.name", String.class);

        // 包下的类
        Set<Class<?>> classes = classUtil.getClasses(authPackageName);

        if (classes == null) {
            return;
        }

        boolean haveClassIgnore;
        boolean haveMethodIgnore;
        boolean haveClassRestController;
        boolean haveMethodRequestMapping;

        StringBuilder stringBuilder = new StringBuilder();

        for (Class<?> clazz : classes) {

            haveClassIgnore = false;
            haveClassRestController = false;

            // 获取类的注解
            Annotation[] annotations1 = clazz.getAnnotations();
            for (Annotation annotation : annotations1) {
                if ("Ignore".equals(annotation.annotationType().getSimpleName())) {
                    haveClassIgnore = true;
                }
                if ("RestController".equals(annotation.annotationType().getSimpleName())) {
                    haveClassRestController = true;
                }
            }

            // 获取方法的注解
            Method[] methods = clazz.getDeclaredMethods();

            if (haveClassRestController) {

                if (haveClassIgnore) {

                    for (Method method : methods) {

                        stringBuilder.delete(0, stringBuilder.length());

                        Class<?>[] parameterTypes = method.getParameterTypes();

                        for (Class parameterType : parameterTypes) {
                            stringBuilder.append(parameterType.getName() + ", ");
                        }

                        if (stringBuilder.length() > 2) {
                            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                        }

                        AuthBean.method.put(clazz.getName() + " - " + method.getName() + " - " + stringBuilder.toString(), haveClassRestController && haveClassIgnore);

                    }

                } else {

                    for (Method method : methods) {

                        haveMethodIgnore = false;
                        haveMethodRequestMapping = false;

                        Annotation[] annotations2 = method.getDeclaredAnnotations();
                        for (Annotation annotation : annotations2) {
                            if ("Ignore".equals(annotation.annotationType().getSimpleName())) {
                                haveMethodIgnore = true;
                            }
                            if ("RequestMapping".equals(annotation.annotationType().getSimpleName())) {
                                haveMethodRequestMapping = true;
                            }
                        }

                        if (haveMethodRequestMapping) {

                            stringBuilder.delete(0, stringBuilder.length());

                            Class<?>[] parameterTypes = method.getParameterTypes();

                            for (Class parameterType : parameterTypes) {
                                stringBuilder.append(parameterType.getName() + ", ");
                            }

                            if (stringBuilder.length() > 2) {
                                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                            }

                            AuthBean.method.put(clazz.getName() + " - " + method.getName() + " - " + stringBuilder.toString(), haveMethodIgnore);

                        }

                    }

                }

            }

        }

        System.out.println(jacksonUtil.toString(AuthBean.method));

    }

}
