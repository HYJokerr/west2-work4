package com.niumo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class fatherComment {
    private Integer id;
    private String content;
    private Integer userId;
    private String username;
    private String avatar;
    private List<childComment> childComment;
}
