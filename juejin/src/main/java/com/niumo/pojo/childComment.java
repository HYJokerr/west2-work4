package com.niumo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class childComment {
    private Integer id;
    private String content;
    private Integer userId;
    private String username;
    private String avatar;

}
