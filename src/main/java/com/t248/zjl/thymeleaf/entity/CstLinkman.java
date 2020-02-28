package com.t248.zjl.thymeleaf.entity;

import javax.persistence.*;

@Entity
@Table(name = "cst_linkman")
public class CstLinkman {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "lkm_id")
    private Long lkmId;
//    @Column(name = "lkm_cust_no")
//    private String lkmCustNo;

    @ManyToOne(targetEntity = CstCustomer.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "lkm_cust_no")
    private  CstCustomer cstCustomer;

    public CstCustomer getCstCustomer() {
        return cstCustomer;
    }

    public void setCstCustomer(CstCustomer cstCustomer) {
        this.cstCustomer = cstCustomer;
    }

    @Column(name = "lkm_cust_name")
    private String lkmCustName;
    @Column(name = "lkm_name")
    private String lkmName;
    @Column(name = "lkm_sex")
    private String lkmSex;
    @Column(name = "lkm_postion")
    private String lkmPostion;
    @Column(name = "lkm_tel")
    private String lkmTel;
    @Column(name = "lkm_mobile")
    private String lkmMobile;
    @Column(name = "lkm_memo")
    private String lkmMemo;

    public Long getLkmId() {
        return lkmId;
    }

    public void setLkmId(Long lkmId) {
        this.lkmId = lkmId;
    }

//    public String getLkmCustNo() {
//        return lkmCustNo;
//    }
//
//    public void setLkmCustNo(String lkmCustNo) {
//        this.lkmCustNo = lkmCustNo == null ? null : lkmCustNo.trim();
//    }

    public String getLkmCustName() {
        return lkmCustName;
    }

    public void setLkmCustName(String lkmCustName) {
        this.lkmCustName = lkmCustName == null ? null : lkmCustName.trim();
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName == null ? null : lkmName.trim();
    }

    public String getLkmSex() {
        return lkmSex;
    }

    public void setLkmSex(String lkmSex) {
        this.lkmSex = lkmSex == null ? null : lkmSex.trim();
    }

    public String getLkmPostion() {
        return lkmPostion;
    }

    public void setLkmPostion(String lkmPostion) {
        this.lkmPostion = lkmPostion == null ? null : lkmPostion.trim();
    }

    public String getLkmTel() {
        return lkmTel;
    }

    public void setLkmTel(String lkmTel) {
        this.lkmTel = lkmTel == null ? null : lkmTel.trim();
    }

    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile == null ? null : lkmMobile.trim();
    }

    public String getLkmMemo() {
        return lkmMemo;
    }

    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo == null ? null : lkmMemo.trim();
    }
}