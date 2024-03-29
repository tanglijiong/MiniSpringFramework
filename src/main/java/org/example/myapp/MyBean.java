package org.example.myapp;

import org.example.minispringframework.annotation.Autowired;
import org.example.minispringframework.annotation.Component;

@Component
public class MyBean {
    @Autowired
    private InnerBean innerBean;
     private String name;
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

    public InnerBean getInnerBean(){
        return this.innerBean;
    }

}
