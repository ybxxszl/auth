package com.github.ybxxszl.util;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author wjy
 * @desc Properties工具类
 * @date 2019/7/10
 */
@Component
public class PropertiesUtil implements EnvironmentAware {

    private Environment environment;

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * @param key 配置键名称
     * @param c   配置值类型
     * @return
     * @desc 配置文件中，根据key获取value
     * @date 2019/7/10
     * @author wjy
     */
    public <T> T getProperty(String key, Class<T> c) {
        return environment.getProperty(key, c);
    }

    /**
     * @param key 配置键名称
     * @param c   配置值类型
     * @param t   配置默认值
     * @return
     * @desc 配置文件中，根据key获取value，获取不到则返回默认值
     * @date 2019/7/10
     * @author wjy
     */
    public <T> T getProperty(String key, Class<T> c, T t) {
        return environment.getProperty(key, c, t);
    }

}
