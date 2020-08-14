package com.hitman_zhang.datasource.modules.game.pojo;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class GamePojo  implements Serializable {
    private static final long serialVersionUID = 3270498794904883321L;

    private int id;

    private String name;

    private String type;

}
