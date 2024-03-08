package com.niumo.controller;

import com.niumo.pojo.Article;
import com.niumo.pojo.Result;
import com.niumo.pojo.User;
import com.niumo.service.UserService;
import com.niumo.utils.JwtUtil;
import com.niumo.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    //用户注册
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        log.info("用户注册");

        String username = user.getUsername();
        String password = user.getPassword();

        User u = userService.searchUser(username);

        //判断输入不为空
        if(username==null||password==null){
            return Result.error("用户名和密码不能为空");
        }

        //查询用户名是否存在
        if(u != null){
            return Result.error("用户名被占用");
        }

        userService.register(username,password);
        return Result.success();
    }

    //用户登录
    @PostMapping("/login")
    public Result<String> login(@RequestBody User user){
        log.info("用户登录");

        String username = user.getUsername();
        String password = user.getPassword();

        //查询用户名
        User u = userService.searchUser(username);

        if(u==null){
            return Result.error("用户不存在");
        }

        //判断密码是否正确
        if(Objects.equals(password, u.getPassword())){

            Map<String,Object> claims = new HashMap<>();
            claims.put("id",u.getId());
            claims.put("username",u.getUsername());
            String token = JwtUtil.genToken(claims);


            return Result.success(token);
        }else{
            return Result.error("密码错误");
        }


    }

    //根据用户id查询用户信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        log.info("获取用户信息");

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        User user = userService.searchUserById(id);

        return Result.success(user);

    }

    //更新用户信息（废弃）
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        log.info("更新用户信息");

        userService.updata(user);
        return Result.success();
    }

    //更新用户名
    @PutMapping("/username")
    public Result updateUsername(@RequestBody User user){
        log.info("更新用户名");

        String username = user.getUsername();

        //判断用户名是否为存在
        User u = userService.searchUser(username);
        if(u!=null){
            return Result.error("用户名被占用");
        }

        userService.UpdateUsername(username);
        return Result.success();
    }

    //更新密码
    @PutMapping("/password")
    public Result updatePassword(@RequestBody User user){
        log.info("更新密码");

        String password = user.getPassword();

        userService.updatePassword(password);
        return Result.success();
    }

    //更新用户简介
    @PutMapping("/biography")
    public Result updateBiography(@RequestBody User user){
        log.info("更新用户简介");

        String biography = user.getBiography();

        userService.updateBiography(biography);
        return Result.success();
    }

    //更新用户头像
    @PutMapping("/updateAvatar")
    public Result updateAvatar(@RequestBody User user){
        log.info("更新用户头像");

        String avatar = user.getAvatar();
        System.out.println("头像地址：" + avatar);

        userService.updateAvatar(avatar);
        return Result.success();
    }

    //获取用户写的文章的id
    @GetMapping("/getArticle")
    public Result<List<Integer>> getArticle(){
        log.info("获取用户写的文章的id");

        List<Integer> list = userService.getArticleIdByUserId();

        return Result.success(list);

    }

    //获取用户点赞的文章的id
    @GetMapping("/likes")
    public Result<List<Integer>> getLikesArticle(){
        log.info("获取用户点赞的文章的id");

        List<Integer> list = userService.getArticleIdByLikes();

        return Result.success(list);

    }

}
