package com.t248.zjl.thymeleaf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_right")
public class Right implements Serializable {
    @Id
    @Column(name = "right_code")
    private String rightCode;
    @Column(name = "right_parent_code")
    private String rightParentCode;
    @Column(name = "right_type")
    private String right_type;
    @Column(name = "right_text")
    private String rightText;
    @Column(name = "right_url")
    private String rightUrl;
    @Column(name = "right_tip")
    private String rightTip;
    @ManyToMany(mappedBy = "rights")
    @JsonIgnore
    private Set<Role> roles=new HashSet<Role>();

    public String getRightCode() {
        return rightCode;
    }

    public void setRightCode(String rightCode) {
        this.rightCode = rightCode;
    }

    public String getRightParentCode() {
        return rightParentCode;
    }

    public void setRightParentCode(String rightParentCode) {
        this.rightParentCode = rightParentCode;
    }

    public String getRight_type() {
        return right_type;
    }

    public void setRight_type(String right_type) {
        this.right_type = right_type;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public String getRightUrl() {
        return rightUrl;
    }

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
    }

    public String getRightTip() {
        return rightTip;
    }

    public void setRightTip(String rightTip) {
        this.rightTip = rightTip;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
