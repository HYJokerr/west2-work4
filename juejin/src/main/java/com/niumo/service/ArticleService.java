package com.niumo.service;

import com.niumo.pojo.Article;
import com.niumo.pojo.childComment;
import com.niumo.pojo.fatherComment;

import java.util.List;

public interface ArticleService {
    void create(Article article);

    Article getArticle(Integer id);

    void likes(Integer id);

    List<Integer> listByClicks();

    List<Integer> listByTime();

    void addComment(Integer id,String content, String username,String avatar);

    void addComment2(Integer id, String content, String username,String avatar);


    List<fatherComment> getFatherComment(Integer id);

    List<childComment> getChildComment(Integer id);
}
