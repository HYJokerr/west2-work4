package com.niumo.mapper;


import com.niumo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {


    //根据用户名查询用户信息
    @Select("select * from user where username=#{username}")
    User seachUser(String username);

    //根据用户id查询用户信息
    @Select("select * from user where id=#{id}")
    User searchUserById(Integer id);

    //添加用户
    //@Options(keyProperty = "id",useGeneratedKeys = true)
    @Insert("insert into user(username,password,biography,avatar) " +
            "values(#{username},#{password},#{biography},#{avatar})")
    void register(String username, String password,String biography,String avatar) ;

    //更新用户所有信息
    @Update("update user set username=#{username},password=#{password},biography=#{biography} where id=#{id}")
    void updata(User user);

    //更新用户名
    @Update("update user set username=#{username} where id=#{id}")
    void updateUsername(Integer id, String username);

    //更新密码
    @Update("update user set password=#{password} where id=#{id}")
    void updatePassword(String password, Integer id);

    //更新用户简介
    @Update("update user set biography=#{biography} where id=#{id}")
    void updateBiography(String biography, Integer id);

    //更新头像
    @Update("update user set avatar=#{avatarUrl} where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    //根据用户id查询所写的文章id
    @Select("select id from article where user_id=#{userid}")
    List<Integer> getArticleIdByUserId(Integer userId);

    //根据用户id查询点赞的文章id
    @Select("select article_id from likes where user_id=#{userid}")
    List<Integer> getArticleIdByLikes(Integer userId);



}
