package org.example.minispringframework.beans;

public class BeanDefinition {
    private Class<?> beanClass; // Bean的类类型
    private String scope; // Bean的作用域

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.scope = "singleton"; // 默认为单例
    }

    // Bean类类型的getter和setter
    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    // Bean作用域的getter和setter
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    // 判断是否为单例
    public boolean isSingleton() {
        return "singleton".equals(scope);
    }

    // 判断是否为原型（每次请求都创建新的实例）
    public boolean isPrototype() {
        return "prototype".equals(scope);
    }
}
