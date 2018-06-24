package com.huayutech.customauthorization.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Role {

    @Id
    @Column(length=36)
    protected String id;

    @Column
    protected String name;

    @Column
    protected String alias;

}
