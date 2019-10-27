package com.github.ybxxszl.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ybxxszl.config.JacksonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wjy
 * @desc Jackson工具类
 * @date 2019/7/10
 */
@Component
public class JacksonUtil {

    private static ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private JacksonConfig config;

    public ObjectMapper getObjectMapper() {
        return config.getObjectMapper(mapper);
    }

    /**
     * @desc String转Entity
     * @date 2019/7/10
     * @author wjy
     */
    public <T> T toEntity(String content, Class<T> valueType) {
        try {
            return getObjectMapper().readValue(content, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @desc Object转String
     * @date 2019/7/10
     * @author wjy
     */
    public String toString(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
