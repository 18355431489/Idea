package com.pojo;

/**
 * @description: TODO
 * @author: 唐小甫
 * @create: 2020/5/29 22:05
 * @version: 1.0
 */
public class User {

    private Long id;
    private String name;
    private Integer age;
    private Boolean male;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", male=" + male +
                '}';
    }
}