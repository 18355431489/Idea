package com.entity;

/**
 * 
 * @author 唐小甫
 * @datetime: 2020-11-26 21:21:37
 */
public class GoodsParent {

    private Long uuid;
    
    private String name;

    public GoodsParent() {
        this.uuid = 7589265912375L;
        this.name = "刘德华";
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GoodsParent [uuid=" + uuid + ", name=" + name + "]";
    }
    
    
}