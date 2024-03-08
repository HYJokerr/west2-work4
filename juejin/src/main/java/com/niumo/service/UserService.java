package com.niumo.service;

import com.niumo.pojo.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    //查询用户是否存在
    User searchUser(String username);

    //根据id查询用户信息
    User searchUserById(Integer id);

    //注册
    void register(String username, String password);

    //更新
    void updata(User user);

    //更新头像
    void updateAvatar(String avatarUrl);

    //获取文章id
    List<Integer> getArticleIdByUserId();

    List<Integer> getArticleIdByLikes();

    void UpdateUsername(String username);

    void updatePassword(String password);

    void updateBiography(String biography);
}
