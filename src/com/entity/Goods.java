package com.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Goods {
    
    private Long uuid;

    private String name;

    private String origin;

    private String producer;

    private String unit;

    private BigDecimal inprice;

    private BigDecimal outprice;

    private Integer goodstypeuuid;
    
    private Date productDate;
    
    public Goods() {
        
    }

    public Goods(String name) {
        this.uuid = 8432175391734252L;
        this.name = name;
        this.origin = "西江内蒙古呼伦贝尔大草原";
        this.producer = "三鹿集团有限公司";
        this.unit = "婴儿奶粉";
        this.inprice = new BigDecimal("2658648932845433");
        this.outprice = new BigDecimal("32644689085455791437");
        this.goodstypeuuid = 92760175;
        this.productDate = new Date();
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
        this.name = name == null ? null : name.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getInprice() {
        return inprice;
    }

    public void setInprice(BigDecimal inprice) {
        this.inprice = inprice;
    }

    public BigDecimal getOutprice() {
        return outprice;
    }

    public void setOutprice(BigDecimal outprice) {
        this.outprice = outprice;
    }

    public Integer getGoodstypeuuid() {
        return goodstypeuuid;
    }

    public void setGoodstypeuuid(Integer goodstypeuuid) {
        this.goodstypeuuid = goodstypeuuid;
    }
    
    public String getProductDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return productDate == null ? null : sdf.format(productDate);
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    @Override
    public String toString() {
        return "Goods [uuid=" + uuid
                + ", name=" + name
                + ", origin=" + origin
                + ", producer=" + producer
                + ", unit=" + unit
                + ", inprice=" + inprice
                + ", outprice=" + outprice
                + ", goodstypeuuid=" + goodstypeuuid
                + ", productDate=" + productDate
                + "]";
    }
    
    
}