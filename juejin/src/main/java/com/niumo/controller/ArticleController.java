package com.niumo.controller;

import com.niumo.pojo.*;
import com.niumo.service.ArticleService;
import com.niumo.service.UserService;
import com.niumo.utils.JwtUtil;
import com.niumo.utils.MarkdownToTextUtil;
import com.niumo.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    //创建文章
    @PostMapping("/create")
    public Result create(@RequestBody Article article){
        log.info("创建文章");

        article.setContent(MarkdownToTextUtil.convertMarkdownToPlainText(article.getMarkdown()));

        articleService.create(article);
        return Result.success();
    }

    //获取文章
    @GetMapping("/{id}")
    public Result getArticle(@PathVariable Integer id){
        log.info("获取文章");

        Article article = articleService.getArticle(id);
        if(article == null){
            return Result.error("文章不存在");
        }

        System.out.println(article);

        return Result.success(article);
    }

    //点赞文章
    @PutMapping("/{id}/likes")
    public Result likes(@PathVariable Integer id){
        log.info("点赞文章");

        articleService.likes(id);
        return Result.success();
    }

    //文章按clicks排序并返回id
    @GetMapping("/listByClicks")
    public Result<List<Integer>> listByClicks(){
        log.info("文章按Clicks排序并返回id");
        List<Integer> list = articleService.listByClicks();

        return Result.success(list);
    }

    //文章按时间排序并返回id
    @GetMapping("/listByTime")
    public Result<List<Integer>> listByTime(){
        log.info("文章按时间排序并返回id");

        List<Integer> list = articleService.listByTime();

        return Result.success(list);
    }

    //评论文章
    @PostMapping("/{id}/comment")
    public Result comment(@PathVariable Integer id, @RequestBody Article article){
        log.info("评论文章");
        String content = article.getContent();

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        User user = userService.searchUserById(userId);
        String username = user.getUsername();
        String avatar = user.getAvatar();

        articleService.addComment(id,content,username,avatar);
        return Result.success();
    }

    //评论评论
    @PostMapping("/comment/{id}")
    public Result comment2(@PathVariable Integer id, @RequestBody Article article){
        log.info("评论评论");

        String content = article.getContent();

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        User user = userService.searchUserById(userId);
        String username = user.getUsername();
        String avatar = user.getAvatar();

        articleService.addComment2(id,content,username,avatar);
        return Result.success();
    }

    //获取主评论
    @GetMapping("/{id}/getComment")
    public Result<List<fatherComment>> getFatherComment(@PathVariable Integer id){
        log.info("获取主评论");
        System.out.println("文章id："+id);
        List<fatherComment> list = articleService.getFatherComment(id);
        System.out.println(list);

        return Result.success(list);
    }

    //获取子评论
    @GetMapping("/comment/{id}")
    public Result<List<childComment>> getFatherComment2(@PathVariable Integer id){
        log.info("获取子评论");

        List<childComment> list = articleService.getChildComment(id);

        return Result.success(list);
    }


}
