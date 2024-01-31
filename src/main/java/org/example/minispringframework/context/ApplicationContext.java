package org.example.minispringframework.context;

import org.example.minispringframework.core.BeanFactory;

import java.io.IOException;

public class ApplicationContext {
    private BeanFactory beanFactory;

    public ApplicationContext(String basePackage) throws IOException, ClassNotFoundException {
        beanFactory = new BeanFactory();
        beanFactory.scanPackageAndLoadBeans(basePackage);
    }

    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }
}

