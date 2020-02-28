package com.t248.zjl.thymeleaf.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders_line")
public class OrdersLine {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "odd_id")
    private Long oddId;
    @Column(name = "odd_order_id")
    private Long oddOrderId;
    @Column(name = "odd_prod_id")
    private Long oddProdId;
    @Column(name = "odd_count")
    private Integer oddCount;
    @Column(name = "odd_unit")
    private String oddUnit;
    @Column(name = "odd_price")
    private BigDecimal oddPrice;

    public Long getOddId() {
        return oddId;
    }

    public void setOddId(Long oddId) {
        this.oddId = oddId;
    }

    public Long getOddOrderId() {
        return oddOrderId;
    }

    public void setOddOrderId(Long oddOrderId) {
        this.oddOrderId = oddOrderId;
    }

    public Long getOddProdId() {
        return oddProdId;
    }

    public void setOddProdId(Long oddProdId) {
        this.oddProdId = oddProdId;
    }

    public Integer getOddCount() {
        return oddCount;
    }

    public void setOddCount(Integer oddCount) {
        this.oddCount = oddCount;
    }

    public String getOddUnit() {
        return oddUnit;
    }

    public void setOddUnit(String oddUnit) {
        this.oddUnit = oddUnit == null ? null : oddUnit.trim();
    }

    public BigDecimal getOddPrice() {
        return oddPrice;
    }

    public void setOddPrice(BigDecimal oddPrice) {
        this.oddPrice = oddPrice;
    }
}