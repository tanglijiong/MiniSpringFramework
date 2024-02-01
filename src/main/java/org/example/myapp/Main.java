package org.example.myapp;

import org.example.minispringframework.context.ApplicationContext;

public class Main {
    public static void main(String[] args) {
        try {
            ApplicationContext context = new ApplicationContext("org.example.myapp");
            MyBean bean = (MyBean) context.getBean("mybean");
            // 使用bean...
            System.out.println(bean.getInnerBean().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}