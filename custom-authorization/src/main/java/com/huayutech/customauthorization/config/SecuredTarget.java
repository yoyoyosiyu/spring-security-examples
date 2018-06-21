package com.huayutech.customauthorization.config;

import lombok.Data;

@Data
public class SecuredTarget {

    public String id;
    public String[] patterns;
    public String[] rolesName;

}
