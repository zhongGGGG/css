package com.t248.zjl.thymeleaf.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cst_activity")
public class CstActivity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "atv_id")
    private Long atvId;
    @ManyToOne(targetEntity = CstCustomer.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "atv_cust_no")
    private  CstCustomer cstCustomer;

    public CstCustomer getCstCustomer() {
        return cstCustomer;
    }

    public void setCstCustomer(CstCustomer cstCustomer) {
        this.cstCustomer = cstCustomer;
    }

    //    @Column(name = "atv_cust_no")
//    private String atvCustNo;
    @Column(name = "atv_cust_name")
    private String atvCustName;
    @Column(name = "atv_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date atvDate;
    @Column(name = "atv_place")
    private String atvPlace;
    @Column(name = "atv_title")
    private String atvTitle;
    @Column(name = "atv_desc")
    private String atvDesc;

    public Long getAtvId() {
        return atvId;
    }

    public void setAtvId(Long atvId) {
        this.atvId = atvId;
    }

//    public String getAtvCustNo() {
//        return atvCustNo;
//    }
//
//    public void setAtvCustNo(String atvCustNo) {
//        this.atvCustNo = atvCustNo == null ? null : atvCustNo.trim();
//    }

    public String getAtvCustName() {
        return atvCustName;
    }

    public void setAtvCustName(String atvCustName) {
        this.atvCustName = atvCustName == null ? null : atvCustName.trim();
    }

    public Date getAtvDate() {
        return atvDate;
    }

    public void setAtvDate(Date atvDate) {
        this.atvDate = atvDate;
    }

    public String getAtvPlace() {
        return atvPlace;
    }

    public void setAtvPlace(String atvPlace) {
        this.atvPlace = atvPlace == null ? null : atvPlace.trim();
    }

    public String getAtvTitle() {
        return atvTitle;
    }

    public void setAtvTitle(String atvTitle) {
        this.atvTitle = atvTitle == null ? null : atvTitle.trim();
    }

    public String getAtvDesc() {
        return atvDesc;
    }

    public void setAtvDesc(String atvDesc) {
        this.atvDesc = atvDesc == null ? null : atvDesc.trim();
    }
}