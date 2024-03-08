package com.niumo.mapper;

import com.niumo.pojo.Article;
import com.niumo.pojo.childComment;
import com.niumo.pojo.fatherComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增文章
    @Insert("insert into article(picture,datetime,title,content,user_id,likes,author_name,author_avator,clicks,markdown,comment) " +
            "values(#{picture},#{datetime},#{title},#{content},#{userId},#{likes},#{authorName},#{authorAvatar},#{clicks},#{markdown},#{comment})")
    void create(Article article);

    //增加点击量
    @Update("update article set clicks=clicks+1 where id=#{id}")
    void addClicks(Integer id);

    //根据id查询文章
    @Select("select * from article where id=#{id}")
    Article getArticle(Integer id);

    //点赞
    @Insert("insert into likes(user_id, article_id) " +
            "values(#{userId},#{articleId}) ")
    void likes(Integer articleId,Integer userId);

    //点赞+1
    @Update("update article set likes=likes+1 where id=#{articleId}")
    void likesAdd(Integer articleId);

    //根据点击量查询文章排行
    @Select("select id from article order by clicks DESC ")
    List<Integer> listByClicks();

    //根据时间查询文章排行
    @Select("select id from article order by datetime DESC ")
    List<Integer> listByTime();

    //增加主评论
    @Insert("insert into comment(article_id,content,user_id,username,avatar) " +
            "values(#{id},#{content},#{userId},#{username},#{avatar})")
    void addComment(Integer id, String content, Integer userId, String username, String avatar);

    //增加子评论
    @Insert("insert into comment(comment_id,content,user_id,username,avatar) " +
            "values(#{id},#{content},#{userId},#{username},#{avatar})")
    void addComment2(Integer id, String content, Integer userId,String username,String avatar);

    //查询主评论
    @Select("select id,content,user_id,username,avatar from comment where article_id=#{id}")
    List<fatherComment> getFatherComment(Integer id);

    //查询子评论
    @Select("select id,content,user_id,username,avatar from comment where comment_id=#{id}")
    List<childComment> getChildComment(Integer id);

    //根据用户id查询头像
    @Select("select avatar from user where id=#{userId}")
    String getAvatarByUserid(Integer userId);

    //增加文章评论数
    @Update("update article set comment=comment+1 where id=#{id}")
    void commentAdd(Integer id);

    //根据子评论id查询文章id
    @Select("select article_id from comment where id=#{id}")
    Integer findArticleId(Integer id);

    @Select("select id from likes where user_id=#{userId} and article_id=#{articleId}")
    Integer findLikes(Integer userId, Integer articleId);
}
