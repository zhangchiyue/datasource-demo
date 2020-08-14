package com.hitman_zhang.datasource.modules.book.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BookPojo implements Serializable {
    private static final long serialVersionUID = 1458483643669723638L;

    private int id;

    private String name;

    private int code;

}
