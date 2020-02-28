package com.t248.zjl.thymeleaf.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cst_service")
public class CstService {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "svr_id")
    private Long svrId;
    @Column(name = "svr_type")
    private String svrType;

    public String getCou() {
        return cou;
    }

    public void setCou(String cou) {
        this.cou = cou;
    }

    @Column(name = "cou")
    private String cou;

    @Column(name = "svr_title")
    private String svrTitle;

    @ManyToOne(targetEntity = CstCustomer.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "svr_cust_no")
    private  CstCustomer cstCustomer;

    public CstCustomer getCstCustomer() {
        return cstCustomer;
    }

    public void setCstCustomer(CstCustomer cstCustomer) {
        this.cstCustomer = cstCustomer;
    }
//    @Column(name = "svr_cust_no")
////    private String svrCustNo;


    @Column(name = "svr_cust_name")
    private String svrCustName;
    @Column(name = "svr_status")
    private String svrStatus;
    @Column(name = "svr_request")
    private String svrRequest;
    @Column(name = "svr_create_id")
    private Long svrCreateId;
    @Column(name = "svr_create_by")
    private String svrCreateBy;
    @Column(name = "svr_create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date svrCreateDate;
    @Column(name = "svr_due_id")
    private Long svrDueId;
    @Column(name = "svr_due_to")
    private String svrDueTo;
    @Column(name = "svr_due_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date svrDueDate;
    @Column(name = "svr_deal")
    private String svrDeal;
    @Column(name = "svr_deal_id")
    private Long svrDealId;
    @Column(name = "svr_deal_by")
    private String svrDealBy;
    @Column(name = "svr_deal_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date svrDealDate;
    @Column(name = "svr_result")
    private String svrResult;
    @Column(name = "svr_satisfy")
    private Integer svrSatisfy;

    public Long getSvrId() {
        return svrId;
    }

    public void setSvrId(Long svrId) {
        this.svrId = svrId;
    }

    public String getSvrType() {
        return svrType;
    }

    public void setSvrType(String svrType) {
        this.svrType = svrType == null ? null : svrType.trim();
    }

    public String getSvrTitle() {
        return svrTitle;
    }

    public void setSvrTitle(String svrTitle) {
        this.svrTitle = svrTitle == null ? null : svrTitle.trim();
    }

//    public String getSvrCustNo() {
//        return svrCustNo;
//    }
//
//    public void setSvrCustNo(String svrCustNo) {
//        this.svrCustNo = svrCustNo == null ? null : svrCustNo.trim();
//    }

    public String getSvrCustName() {
        return svrCustName;
    }

    public void setSvrCustName(String svrCustName) {
        this.svrCustName = svrCustName == null ? null : svrCustName.trim();
    }

    public String getSvrStatus() {
        return svrStatus;
    }

    public void setSvrStatus(String svrStatus) {
        this.svrStatus = svrStatus == null ? null : svrStatus.trim();
    }

    public String getSvrRequest() {
        return svrRequest;
    }

    public void setSvrRequest(String svrRequest) {
        this.svrRequest = svrRequest == null ? null : svrRequest.trim();
    }

    public Long getSvrCreateId() {
        return svrCreateId;
    }

    public void setSvrCreateId(Long svrCreateId) {
        this.svrCreateId = svrCreateId;
    }

    public String getSvrCreateBy() {
        return svrCreateBy;
    }

    public void setSvrCreateBy(String svrCreateBy) {
        this.svrCreateBy = svrCreateBy == null ? null : svrCreateBy.trim();
    }

    public Date getSvrCreateDate() {
        return svrCreateDate;
    }

    public void setSvrCreateDate(Date svrCreateDate) {
        this.svrCreateDate = svrCreateDate;
    }

    public Long getSvrDueId() {
        return svrDueId;
    }

    public void setSvrDueId(Long svrDueId) {
        this.svrDueId = svrDueId;
    }

    public String getSvrDueTo() {
        return svrDueTo;
    }

    public void setSvrDueTo(String svrDueTo) {
        this.svrDueTo = svrDueTo == null ? null : svrDueTo.trim();
    }

    public Date getSvrDueDate() {
        return svrDueDate;
    }

    public void setSvrDueDate(Date svrDueDate) {
        this.svrDueDate = svrDueDate;
    }

    public String getSvrDeal() {
        return svrDeal;
    }

    public void setSvrDeal(String svrDeal) {
        this.svrDeal = svrDeal == null ? null : svrDeal.trim();
    }

    public Long getSvrDealId() {
        return svrDealId;
    }

    public void setSvrDealId(Long svrDealId) {
        this.svrDealId = svrDealId;
    }

    public String getSvrDealBy() {
        return svrDealBy;
    }

    public void setSvrDealBy(String svrDealBy) {
        this.svrDealBy = svrDealBy == null ? null : svrDealBy.trim();
    }

    public Date getSvrDealDate() {
        return svrDealDate;
    }

    public void setSvrDealDate(Date svrDealDate) {
        this.svrDealDate = svrDealDate;
    }

    public String getSvrResult() {
        return svrResult;
    }

    public void setSvrResult(String svrResult) {
        this.svrResult = svrResult == null ? null : svrResult.trim();
    }

    public Integer getSvrSatisfy() {
        return svrSatisfy;
    }

    public void setSvrSatisfy(Integer svrSatisfy) {
        this.svrSatisfy = svrSatisfy;
    }
}