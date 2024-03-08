package com.niumo.service.impl;


import com.niumo.mapper.UserMapper;
import com.niumo.pojo.User;
import com.niumo.service.UserService;
import com.niumo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User searchUser(String username) {
        return userMapper.seachUser(username);
    }

    @Override
    public User searchUserById(Integer id) {
        return userMapper.searchUserById(id);
    }

    @Override
    public void register(String username, String password) {

        userMapper.register(username,password,"这个人很神秘，什么都没有写","https://img.zcool.cn/community/01a6095f110b9fa8012066219b67d4.png@1280w_1l_2o_100sh.png");
    }

    @Override
    public void updata(User user) {

        userMapper.updata(user);
    }

    @Override
    public void UpdateUsername(String username) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateUsername(id,username);
    }

    @Override
    public void updatePassword(String password) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePassword(password,id);
    }

    @Override
    public void updateBiography(String biography) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateBiography(biography,id);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public List<Integer> getArticleIdByUserId() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return userMapper.getArticleIdByUserId(userId);

    }

    @Override
    public List<Integer> getArticleIdByLikes() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        return userMapper.getArticleIdByLikes(userId);
    }




}
