package com.github.ybxxszl.request;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class RequestHandle extends RequestFilter {

    @Override
    public boolean handle(HttpServletRequest request) {

        return true;

    }

}
