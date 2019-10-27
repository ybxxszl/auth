package com.github.ybxxszl.request;

import com.github.ybxxszl.bean.AuthBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wjy
 * @desc 请求过滤
 * @date 2019/7/10
 */
@Component
@Aspect
public abstract class RequestFilter {

    /**
     * @desc 使用@Controller注解的方法
     * @date 2019/7/10
     * @author wjy
     */
    @Pointcut(value = "within(@org.springframework.stereotype.Controller *)")
    public void validateController() {
    }

    /**
     * @desc 使用@RestController注解的方法
     * @date 2019/7/10
     * @author wjy
     */
    @Pointcut(value = "within(@org.springframework.web.bind.annotation.RestController *)")
    public void validateRestController() {
    }

    /**
     * @desc 使用@Controller注解和@RestController注解的全部方法
     * @date 2019/7/10
     * @author wjy
     */
    @Pointcut(value = "validateController()||validateRestController()")
    public void validate() {
    }

    @Around(value = "validate()")
    public Object doLoginValidate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        Signature signature = proceedingJoinPoint.getSignature();
        CodeSignature codeSignature = (CodeSignature) signature;

        Class<?>[] parameterTypes = codeSignature.getParameterTypes();

        StringBuilder stringBuilder = new StringBuilder();

        for (Class parameterType : parameterTypes) {
            stringBuilder.append(parameterType.getName() + ", ");
        }

        if (stringBuilder.length() > 2) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        String key = signature.getDeclaringTypeName() + " - " + signature.getName() + " - " + stringBuilder.toString();

        boolean value = AuthBean.method.get(key);

        if (value) {

            boolean flag = handle(request);

            if (flag) {

                return proceedingJoinPoint.proceed();

            }

        }

        response.sendError(401, "请重新登录");

        return null;

    }

    public abstract boolean handle(HttpServletRequest request);

}
