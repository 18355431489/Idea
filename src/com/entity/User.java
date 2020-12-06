package com.entity;

/**
 * 
 * @author 唐小甫
 * @datetime: 2020-12-06 18:53:22
 */
public class User {

    private Long id;
    
    private String username;
    
    private Integer money;
    
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", money=" + money + "]";
    }
    
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Date getKqTime() {
//        return kqTime;
//    }
//
//    public void setKqTime(Date kqTime) {
//        this.kqTime = kqTime;
//    }
//
//    @Override
//    public String toString() {
//        return "User ["
//                + "id=" + id 
//                + ", kqTime=" + kqTime 
//                + "]";
//    }
    
    
}