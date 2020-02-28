package com.t248.zjl.thymeleaf.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cst_lost")
public class CstLost {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "lst_id")
    private Long lstId;
    @Column(name = "lst_cust_no")
    private String lstCustNo;
    @Column(name = "lst_cust_name")
    private String lstCustName;
    @Column(name = "lst_cust_manager_id")
    private Long lstCustManagerId;
    @Column(name = "lst_cust_manager_name")
    private String lstCustManagerName;
    @Column(name = "lst_last_order_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lstLastOrderDate;
    @Column(name = "lst_lost_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lstLostDate;
    @Column(name = "lst_delay")
    private String lstDelay;
    @Column(name = "lst_reason")
    private String lstReason;
    @Column(name = "lst_status")
    private String lstStatus;

    public Long getLstId() {
        return lstId;
    }

    public void setLstId(Long lstId) {
        this.lstId = lstId;
    }

    public String getLstCustNo() {
        return lstCustNo;
    }

    public void setLstCustNo(String lstCustNo) {
        this.lstCustNo = lstCustNo == null ? null : lstCustNo.trim();
    }

    public String getLstCustName() {
        return lstCustName;
    }

    public void setLstCustName(String lstCustName) {
        this.lstCustName = lstCustName == null ? null : lstCustName.trim();
    }

    public Long getLstCustManagerId() {
        return lstCustManagerId;
    }

    public void setLstCustManagerId(Long lstCustManagerId) {
        this.lstCustManagerId = lstCustManagerId;
    }

    public String getLstCustManagerName() {
        return lstCustManagerName;
    }

    public void setLstCustManagerName(String lstCustManagerName) {
        this.lstCustManagerName = lstCustManagerName == null ? null : lstCustManagerName.trim();
    }

    public Date getLstLastOrderDate() {
        return lstLastOrderDate;
    }

    public void setLstLastOrderDate(Date lstLastOrderDate) {
        this.lstLastOrderDate = lstLastOrderDate;
    }

    public Date getLstLostDate() {
        return lstLostDate;
    }

    public void setLstLostDate(Date lstLostDate) {
        this.lstLostDate = lstLostDate;
    }

    public String getLstDelay() {
        return lstDelay;
    }

    public void setLstDelay(String lstDelay) {
        this.lstDelay = lstDelay == null ? null : lstDelay.trim();
    }

    public String getLstReason() {
        return lstReason;
    }

    public void setLstReason(String lstReason) {
        this.lstReason = lstReason == null ? null : lstReason.trim();
    }

    public String getLstStatus() {
        return lstStatus;
    }

    public void setLstStatus(String lstStatus) {
        this.lstStatus = lstStatus == null ? null : lstStatus.trim();
    }
}