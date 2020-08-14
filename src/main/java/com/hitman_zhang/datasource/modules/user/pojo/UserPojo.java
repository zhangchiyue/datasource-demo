package com.hitman_zhang.datasource.modules.user.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserPojo implements Serializable {
    private static final long serialVersionUID = 1867167517820066772L;

    private int id;

    private int age;

    private String name;


}
