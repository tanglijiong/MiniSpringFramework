package org.example.myapp;

import org.example.minispringframework.annotation.Component;

@Component
public class InnerBean {
    private String name="InnerBean1";
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
