package com.t248.zjl.thymeleaf.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "odr_id")
    private Long odrId;
    @Column(name = "odr_customer_no")
    private String odrCustomerNo;
    @Column(name = "odr_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date odrDate;
    @Column(name = "odr_addr")
    private String odrAddr;
    @Column(name = "odr_status")
    private String odrStatus;

    public Long getOdrId() {
        return odrId;
    }

    public void setOdrId(Long odrId) {
        this.odrId = odrId;
    }

    public String getOdrCustomerNo() {
        return odrCustomerNo;
    }

    public void setOdrCustomerNo(String odrCustomerNo) {
        this.odrCustomerNo = odrCustomerNo == null ? null : odrCustomerNo.trim();
    }

    public Date getOdrDate() {
        return odrDate;
    }

    public void setOdrDate(Date odrDate) {
        this.odrDate = odrDate;
    }

    public String getOdrAddr() {
        return odrAddr;
    }

    public void setOdrAddr(String odrAddr) {
        this.odrAddr = odrAddr == null ? null : odrAddr.trim();
    }

    public String getOdrStatus() {
        return odrStatus;
    }

    public void setOdrStatus(String odrStatus) {
        this.odrStatus = odrStatus == null ? null : odrStatus.trim();
    }
}