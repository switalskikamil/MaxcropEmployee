package com.maxcropdata.maxcropemployee.model.pricegroup;

import java.math.BigDecimal;
import java.util.Date;

public class PriceGroup {
    private long id;
    private String name;
    private BigDecimal averageWeight;
    private BigDecimal averageQuantity;
    private BigDecimal price;
    private Date date;
    private BigDecimal minimum;
    private int calcType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(BigDecimal averageWeight) {
        this.averageWeight = averageWeight;
    }

    public BigDecimal getAverageQuantity() {
        return averageQuantity;
    }

    public void setAverageQuantity(BigDecimal averageQuantity) {
        this.averageQuantity = averageQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getMinimum() {
        return minimum;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public int getCalcType() {
        return calcType;
    }

    public void setCalcType(int calcType) {
        this.calcType = calcType;
    }

    @Override
    public String toString() {
        return "PriceGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averageWeight=" + averageWeight +
                ", averageQuantity=" + averageQuantity +
                ", price=" + price +
                ", date=" + date +
                ", minimum=" + minimum +
                ", calcType=" + calcType +
                '}';
    }
}
