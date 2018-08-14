DROP DATABASE IF EXISTS test;
USE test;

DROP TABLE IF EXISTS t_user;
create table t_user
(
  user_id     int auto_increment
    primary key,
  comment '用户id',
  ref_user_id  varchar(32)            not null
  comment 'refId',
  username   varchar(12) default '' not null
  comment '用户名',
  nickname   varchar(12)            null
  comment '用户昵称',
  salt       varchar(128)           not null
  comment '加密盐',
  password   varchar(128)           null
  comment '用户密码',
  sex        tinyint                null
  comment '性别',
  email      varchar(68)            null
  comment '邮箱',
  head_image  varchar(50)            null,
  create_ip   varchar(15)            null
  comment '创建ip',
  create_date bigint(11) default '0' null
  comment '创建时间',
  constraint t_user_ref_user_id_uindex
  unique (ref_user_id),
  constraint t_user_user_id_uindex
  unique (user_id)
);


DROP TABLE IF EXISTS t_article;
create table t_article
(
  article_id       int(11) unsigned auto_increment
    primary key,
  ref_article_id    varchar(32)                   not null,
  ref_user_id       varchar(32) default ''        not null
  comment '用户(作者)refUserId',
  title           varchar(100)                  not null
  comment '文章标题',
  description     varchar(130) default ''       not null
  comment '文章简介',
  md_content       text                          null
  comment 'md',
  content         text                          not null
  comment '文章内容',
  cover_image      varchar(100) default ''       not null
  comment '封面图片,加上缩略图路径70个字段足够',
  count_view       int default '0'               null
  comment '浏览总数',
  count_comment    int default '0'               null
  comment '评论总数',
  count_collection int default '0'               null
  comment '收藏总数',
  type            varchar(10)                   not null
  comment '文章分类',
  status          tinyint default '0'           not null
  comment '文章状态,0:正常,1:删除',
  create_ip        varchar(15) default '0.0.0.1' null
  comment '创建ip',
  create_date      bigint(11) default '0'        null
  comment '创建时间',
  update_date      bigint(11) default '0'        null
  comment '更新时间'
);

DROP TABLE IF EXISTS t_article_comment;
create table t_article_comment
(
  comment_id    int(11) unsigned auto_increment
  comment '评论id'
    primary key,
  ref_comment_id varchar(32) default ''        not null
  comment 'ref评论id',
  content      text                          not null
  comment '评论内容',
  approve      int default '0'               not null
  comment '赞',
  floor        int default '0'               null
  comment '楼',
  ref_article_id varchar(32) default ''        not null
  comment 'ref文章Id',
  replay_user   varchar(32) default ''        not null
  comment '评论的用户id',
  replay_to     varchar(32) default '""'      not null
  comment '被回复的用户',
  replay_ref_id  varchar(32) default ''        not null
  comment '被回复评论Id,',
  status       tinyint default '0'           not null
  comment '评论状态0:正常,1:删除',
  create_ip     varchar(15) default '0.0.0.1' not null
  comment '创建ip',
  create_date   bigint(11)                    not null
  comment '评论创建时间'
);

DROP TABLE IF EXISTS t_user_collect;
create table t_user_collect
(
  collect_id    int(11) unsigned auto_increment
  comment '收藏id'
    primary key,
  ref_collect_id varchar(32) default '' not null
  comment 'ref收藏id',
  user_id       varchar(32) default '' not null
  comment '用户id',
  link_id       varchar(32) default '' not null
  comment '关联id,文章或者评论',
  status       tinyint                not null
  comment '状态,0:正常1:删除',
  type         tinyint default '1'    not null
  comment '类型1:文章收藏2:文章点赞',
  create_date   bigint(11)             not null
  comment '创建时间',
  constraint userAndArticle
  unique (user_id, link_id)
);