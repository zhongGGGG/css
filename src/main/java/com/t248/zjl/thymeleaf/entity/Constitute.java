package com.t248.zjl.thymeleaf.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Constitute {


    @Id
    @Column(name = "dict_value")
    String valu;
    @Column(name = "dict_item")
    String items;
    @Column(name = "count")
    String count;
}
