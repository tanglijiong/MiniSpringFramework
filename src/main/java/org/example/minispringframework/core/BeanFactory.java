package org.example.minispringframework.core;

import org.example.minispringframework.annotation.Autowired;
import org.example.minispringframework.annotation.Component;
import org.example.minispringframework.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private Map<String, Object> beanCache = new HashMap<>();

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

    private Object createBean(BeanDefinition beanDefinition) {
        try {
            Class<?> beanClass = beanDefinition.getBeanClass();
            Object bean = beanClass.getDeclaredConstructor().newInstance();

            // 依赖注入
            for (Field field : beanClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    String beanName = fieldType.getSimpleName().toLowerCase();
                    Object dependency = getBean(beanName);
                    field.set(bean, dependency);
                }
            }

            // 缓存已创建的Bean
            beanCache.put(beanClass.getSimpleName().toLowerCase(), bean);

            return bean;
        } catch (Exception e) {
            throw new RuntimeException("Bean instantiation error", e);
        }
    }
    public Object getBean(String name) {
        Object bean = beanCache.get(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named '" + name + "' is defined");
        }
        return createBean(beanDefinition);
    }

    public void scanPackageAndLoadBeans(String basePackage) throws IOException, ClassNotFoundException {
        String path = basePackage.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            File directory = new File(resource.getFile());
            for (File file : directory.listFiles()) {
                String fileName = file.getName();
                if (file.isFile() && fileName.endsWith(".class")) {
                    String className = fileName.substring(0, fileName.lastIndexOf("."));
                    Class<?> clazz = Class.forName(basePackage + "." + className);
                    if (clazz.isAnnotationPresent(Component.class)) {
                        BeanDefinition beanDefinition = new BeanDefinition(clazz);
                        registerBeanDefinition(className.toLowerCase(), beanDefinition);
                    }
                }
            }
        }
    }
}