package com.t248.zjl.thymeleaf.entity;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "stk_id")
  private Long stkId;
  @ManyToOne(targetEntity = Product.class,fetch = FetchType.EAGER)
  @JoinColumn(name = "stk_prod_id")
  private Product product;
//  @Column(name = "stk_prod_id")
//  private Long stkProdId;

  @Column(name = "stk_warehouse")
  private String stkWarehouse;
  @Column(name = "stk_ware")
  private String stkWare;
  @Column(name = "stk_count")
  private Long stkCount;
  @Column(name = "stk_memo")
  private String stkMemo;


  public long getStkId() {
    return stkId;
  }

  public void setStkId(long stkId) {
    this.stkId = stkId;
  }


//  public long getStkProdId() {
//    return stkProdId;
//  }
//
//  public void setStkProdId(long stkProdId) {
//    this.stkProdId = stkProdId;
//  }


  public void setStkId(Long stkId) {
    this.stkId = stkId;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setStkCount(Long stkCount) {
    this.stkCount = stkCount;
  }

  public String getStkWarehouse() {
    return stkWarehouse;
  }

  public void setStkWarehouse(String stkWarehouse) {
    this.stkWarehouse = stkWarehouse;
  }


  public String getStkWare() {
    return stkWare;
  }

  public void setStkWare(String stkWare) {
    this.stkWare = stkWare;
  }


  public long getStkCount() {
    return stkCount;
  }

  public void setStkCount(long stkCount) {
    this.stkCount = stkCount;
  }


  public String getStkMemo() {
    return stkMemo;
  }

  public void setStkMemo(String stkMemo) {
    this.stkMemo = stkMemo;
  }

}
