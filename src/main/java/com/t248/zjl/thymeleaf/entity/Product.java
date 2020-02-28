package com.t248.zjl.thymeleaf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long prodId;
    @Column(name = "prod_name")
    private String prodName;
    @Column(name = "prod_type")
    private String prodType;
    @Column(name = "prod_batch")
    private String prodBatch;
    @Column(name = "prod_unit")
    private String prodUnit;
    @Column(name = "prod_price")
    private String prodPrice;
    @Column(name = "prod_memo")
    private String prodMemo;


    /**
     * 一对多关系
     */
    @OneToMany(targetEntity = Storage.class,fetch = FetchType.LAZY,cascade =CascadeType.ALL,mappedBy = "product")
    @JsonIgnore
    private Set<Storage> storages=new HashSet<>();

    public Set<Storage> getStorages() {
        return storages;
    }

    public void setStorages(Set<Storage> storages) {
        this.storages = storages;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    public String getProdBatch() {
        return prodBatch;
    }

    public void setProdBatch(String prodBatch) {
        this.prodBatch = prodBatch == null ? null : prodBatch.trim();
    }

    public String getProdUnit() {
        return prodUnit;
    }

    public void setProdUnit(String prodUnit) {
        this.prodUnit = prodUnit == null ? null : prodUnit.trim();
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdMemo() {
        return prodMemo;
    }

    public void setProdMemo(String prodMemo) {
        this.prodMemo = prodMemo == null ? null : prodMemo.trim();
    }
}