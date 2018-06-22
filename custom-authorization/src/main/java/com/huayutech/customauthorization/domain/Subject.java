package com.huayutech.customauthorization.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Subject {

    @Id
    @Column(length=36)
    protected String id;

    @Column
    protected String name;

}
