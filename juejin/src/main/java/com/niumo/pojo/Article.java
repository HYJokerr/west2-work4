package com.niumo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private String picture;
    private String datetime;
    private Integer likes;
    private String authorName;
    private String authorAvatar;
    private Integer clicks;
    private String markdown;
    private Integer comment;
}
