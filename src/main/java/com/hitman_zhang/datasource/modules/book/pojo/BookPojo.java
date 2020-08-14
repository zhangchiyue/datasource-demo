package com.hitman_zhang.datasource.modules.book.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookPojo {

    private int id;

    private String name;

    private int code;

}
