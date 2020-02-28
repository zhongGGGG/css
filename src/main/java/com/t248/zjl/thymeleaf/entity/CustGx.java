package com.t248.zjl.thymeleaf.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class CustGx {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")*/
    //String custId;
    @Column(name = "dict_item")
    String dictItem;
    @Id
    @Column(name = "money")
    String money;
}
