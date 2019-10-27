package com.github.ybxxszl.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author wjy
 * @desc Class工具类
 * @date 2019/10/17
 */
@Component
public class ClassUtil {

    public Set<Class<?>> getClasses(String packageName) throws Exception {
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                findALL(packageName, filePath, classes);
            }
        }
        return classes;
    }

    public void findALL(String packageName, String packagePath,
                        Set<Class<?>> classes) throws Exception {
        File file = new File(packagePath);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.isDirectory() || file.getName().endsWith(".class");
                }
            });
            for (File f : files) {
                // 目录，继续扫描
                if (f.isDirectory()) {
                    findALL(packageName + "." + f.getName(), f.getAbsolutePath(),
                            classes);
                } else {
                    // 类文件，记录类名
                    String className = f.getName().substring(0, f.getName().length() - 6);
                    classes.add(
                            Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                }
            }
        }
    }

}
