# west2-work4 寒假合作轮



## 接口文档
https://apifox.com/apidoc/shared-6fb68039-c6a7-49d2-a875-9bfc9b000440



## 技术栈

- Mysql数据库
- Mybatis
- SpringBoot
- Docker



## 项目结构

- juejin
- -----.idea
- -----src
- ---------main
- ------------java/com/niumo
- ---------------config            //配置类
- ---------------controller     //控制层
- ---------------interceptors //拦截器
- ---------------mapper         //实现层
- ---------------pojo               //实体类
- ---------------service           //服务层
- ---------------utils                //工具类
- ---------------resources      //配置
- -----pom.xml                    //Maven配置



## 数据库结构

使用Mysql数据库

##### 1.user表

记录用户信息

- id 				用户id 主键
- username  用户名
- password   密码
- biogarphy   个人简介
- avatar	 	头像（使用图片链接储存）

##### 2.article表

记录文章信息

- id 文章id 主键
- title 文章标题
- content 内容
- user_id 创建者id
- picture 封面
- datetime 创建日期
- likes 点赞数
- author_name 作者名字
- author_avator 作者头像
- clicks 点击量
- markdown markdown格式内容

##### 3.comment表

记录评论

- id 评论id 主键
- content 内容
- article_id 评论文章的id
- comment_id 评论评论的id
- user_id 评论者id
- username 评论者名字
- avatar 评论者头像

##### 4.likes表

记录点赞关系

- id 点赞id 主键
- user_id 点赞的用户id
- article_id 被点赞的文章id

## 项目功能介绍

- 用户注册
- 用户登录
- 我的主页
- 更新用户信息
- 获取用户写的文章
- 获取用户点赞的文章
- 创建文章
- 文章页
- 点赞文章
- 文章按点击量排行
- 文章按时间排行
- 评论文章
- 评论评论









