package com.t248.zjl.thymeleaf.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "sys_user")
public class UserBean implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long usrId;
    @Column(name = "usr_name")
    private String usrName;
    @Column(name = "usr_password")
    private String usrPassword;
    @Column(name = "usr_role_id")
    private Long usrRoleId;
    @Column(name = "usr_flag")
    private Long usrFlag;
}
