package com.niumo.service.impl;

import com.niumo.mapper.ArticleMapper;
import com.niumo.pojo.Article;
import com.niumo.pojo.childComment;
import com.niumo.pojo.fatherComment;
import com.niumo.service.ArticleService;
import com.niumo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void create(Article article) {

        String datetime = LocalDateTime.now().toString();

        article.setDatetime(datetime);

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        String username = (String) map.get("username");
        String avatar = articleMapper.getAvatarByUserid(userId);;



        System.out.println(username+avatar);

        article.setUserId(userId);
        article.setLikes(0);
        article.setClicks(0);
        article.setAuthorName(username);
        article.setAuthorAvatar(avatar);
        article.setPicture("http://dummyimage.com/400x400");
        article.setComment(0);

        articleMapper.create(article);
    }

    @Override
    public Article getArticle(Integer id) {
        articleMapper.addClicks(id);

        return articleMapper.getArticle(id);
    }

    @Override
    public void likes(Integer articleId) {

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        if(articleMapper.findLikes(userId,articleId)!=null){
            return;
        }

        articleMapper.likes(articleId,userId);
        articleMapper.likesAdd(articleId);
    }

    @Override
    public List<Integer> listByClicks() {
        return articleMapper.listByClicks();
    }

    @Override
    public List<Integer> listByTime() {
        return articleMapper.listByTime();
    }

    @Override
    public void addComment(Integer id ,String content,String username,String avatar) {

        //文章评论数+1
        articleMapper.commentAdd(id);

        //记录评论者
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        //添加评论
        articleMapper.addComment(id,content,userId,username,avatar);
    }

    @Override
    public void addComment2(Integer id, String content, String username,String avatar) {

        //文章评论数+1
        Integer articleId = articleMapper.findArticleId(id);
        System.out.println(articleId);
        articleMapper.commentAdd(articleId);

        //记录评论者
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        //添加评论
        articleMapper.addComment2(id,content,userId,username,avatar);
    }

    @Override
    public List<fatherComment> getFatherComment(Integer id) {
        List<fatherComment> fatherComment = articleMapper.getFatherComment(id);

        //组装子评论进主评论
        for (fatherComment father : fatherComment) {
            List<childComment> childComment = articleMapper.getChildComment(father.getId());
            father.setChildComment(childComment);
        }

        return fatherComment;
    }

    @Override
    public List<childComment> getChildComment(Integer id) {
        return articleMapper.getChildComment(id);
    }


}
