DROP DATABASE IF EXISTS test;
CREATE DATABASE test DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
USE test;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `ref_user_id` varchar(32) NOT NULL COMMENT 'refId',
  `username` varchar(12) NOT NULL DEFAULT '' COMMENT '用户名',
  `nickname` varchar(12) DEFAULT NULL COMMENT '用户昵称',
  `salt` varchar(128) NOT NULL COMMENT '加密盐',
  `password` varchar(128) DEFAULT NULL COMMENT '用户密码',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `email` varchar(68) DEFAULT NULL COMMENT '邮箱',
  `head_image` varchar(50) DEFAULT NULL,
  `create_ip` varchar(15) DEFAULT NULL COMMENT '创建ip',
  `create_date` bigint(11) DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `h_user_user_id_uindex` (`user_id`),
  UNIQUE KEY `h_user_ref_user_id_uindex` (`ref_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `article_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ref_article_id` varchar(32) NOT NULL,
  `ref_user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户(作者)refUserId',
  `title` varchar(100) NOT NULL COMMENT '文章标题',
  `description` varchar(130) NOT NULL DEFAULT '' COMMENT '文章简介',
  `md_content` text COMMENT 'md',
  `content` text NOT NULL COMMENT '文章内容',
  `cover_image` varchar(100) NOT NULL DEFAULT '' COMMENT '封面图片,加上缩略图路径70个字段足够',
  `count_view` int(11) DEFAULT '0' COMMENT '浏览总数',
  `count_comment` int(11) DEFAULT '0' COMMENT '评论总数',
  `count_collection` int(11) DEFAULT '0' COMMENT '收藏总数',
  `type` varchar(10) NOT NULL COMMENT '文章分类',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '文章状态,0:正常,1:删除',
  `create_ip` varchar(15) DEFAULT '0.0.0.1' COMMENT '创建ip',
  `create_date` bigint(11) DEFAULT '0' COMMENT '创建时间',
  `update_date` bigint(11) DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_article_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_article_comment`;
CREATE TABLE `t_article_comment` (
  `comment_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `ref_comment_id` varchar(32) NOT NULL DEFAULT '' COMMENT 'ref评论id',
  `content` text NOT NULL COMMENT '评论内容',
  `approve` int(11) NOT NULL DEFAULT '0' COMMENT '赞',
  `floor` int(11) DEFAULT '0' COMMENT '楼',
  `ref_article_id` varchar(32) NOT NULL DEFAULT '' COMMENT 'ref文章Id',
  `replay_user` varchar(32) NOT NULL DEFAULT '' COMMENT '评论的用户id',
  `replay_to` varchar(32) NOT NULL DEFAULT '""' COMMENT '被回复的用户',
  `replay_ref_id` varchar(32) NOT NULL DEFAULT '' COMMENT '被回复评论Id,',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '评论状态0:正常,1:删除',
  `create_ip` varchar(15) NOT NULL DEFAULT '0.0.0.1' COMMENT '创建ip',
  `create_date` bigint(11) NOT NULL COMMENT '评论创建时间',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_user_collect`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_collect`;
CREATE TABLE `t_user_collect` (
  `collect_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `ref_collect_id` varchar(32) NOT NULL DEFAULT '' COMMENT 'ref收藏id',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `link_id` varchar(32) NOT NULL DEFAULT '' COMMENT '关联id,文章或者评论',
  `status` tinyint(4) NOT NULL COMMENT '状态,0:正常1:删除',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型1:文章收藏2:文章点赞',
  `create_date` bigint(11) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`collect_id`),
  UNIQUE KEY `userAndArticle` (`user_id`,`link_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;