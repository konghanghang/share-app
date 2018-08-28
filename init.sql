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

-- ----------------------------
--  Table structure for `wx_mp`
-- ----------------------------
DROP TABLE IF EXISTS `wx_mp`;
CREATE TABLE `wx_mp` (
  `mp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'MpID',
  `account` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '帐户',
  `account_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户帐号',
  `app_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '应用ID',
  `app_secret` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '应用密钥',
  `token` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '服务器认证Token',
  `access_token` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '获取到的凭证',
  `expires_in` bigint(11) DEFAULT '0' COMMENT '凭证有效时间，单位：秒',
  `jsapi_ticket` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'JSSDK',
  `jsapi_ticket_expires_in` bigint(11) DEFAULT '0' COMMENT 'JSSDK过期时间',
  `js_oauth_url` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'JS接口安全域名',
  `api_ticket` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'api_ticket，卡券接口中签名所需凭证',
  `api_ticket_expires_in` bigint(11) DEFAULT '0' COMMENT 'api_ticket过期时间',
  `mch_id` varchar(20) DEFAULT '' COMMENT '微信支付ID',
  `wx_pay_secret` varchar(60) DEFAULT '' COMMENT '微信支付密钥',
  `wx_pay_notify_url` varchar(120) DEFAULT '',
  `wx_pay_cert_path` varchar(60) DEFAULT '' COMMENT '微信支付证书路径',
  `create_date` bigint(11) NOT NULL DEFAULT '0' COMMENT 'WHO字段',
  `create_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0.0.0.0' COMMENT 'WHO字段',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'SYSTEM' COMMENT 'WHO字段',
  `last_update_date` bigint(13) DEFAULT '0' COMMENT 'WHO字段',
  `last_update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'WHO字段',
  `web_app_id` varchar(30) DEFAULT '' COMMENT '微信登陆网页应用的appId',
  `web_app_secret` varchar(60) DEFAULT '' COMMENT '微信登录网页应用的secret',
  PRIMARY KEY (`mp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 PACK_KEYS=0 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `h_wechat_user`
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `app_id` varchar(60) NOT NULL DEFAULT '' COMMENT '当前微信AppId',
  `open_id` varchar(60) NOT NULL DEFAULT '' COMMENT '用户的唯一标识',
  `union_id` varchar(60) DEFAULT '' COMMENT '开放平台统一ID',
  `nickname` varchar(80) DEFAULT '' COMMENT '昵称',
  `sex` tinyint(4) DEFAULT '0' COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `province` varchar(20) DEFAULT '' COMMENT '用户个人资料填写的省份',
  `city` varchar(20) DEFAULT '' COMMENT '普通用户个人资料填写的城市',
  `country` varchar(20) DEFAULT '' COMMENT '国家，如中国为CN',
  `language` varchar(10) DEFAULT 'zh_CN',
  `head_img_url` varchar(240) DEFAULT '' COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
  `subscribe` tinyint(4) DEFAULT '0',
  `subscribe_time` bigint(11) DEFAULT '0',
  `create_date` bigint(11) NOT NULL DEFAULT '0' COMMENT 'WHO字段',
  `last_update_date` bigint(11) DEFAULT '0' COMMENT 'WHO字段',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `wx_open_id` (`open_id`),
  KEY `app_id` (`app_id`),
  KEY `union_id` (`union_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 PACK_KEYS=0 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `m_menses`
-- ----------------------------
DROP TABLE IF EXISTS `m_menses`;
CREATE TABLE `m_menses` (
  `menses_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键Id,自增',
  `year` varchar(5) DEFAULT NULL COMMENT '年',
  `month` varchar(5) DEFAULT NULL COMMENT '月份',
  `open_id` varchar(32) DEFAULT '' COMMENT '微信openId',
  `last_menses_time` bigint(11) DEFAULT '0' COMMENT '上一次月经时间',
  `menses_time` bigint(11) DEFAULT '0' COMMENT '预测这一次的时间',
  `true_menses_time` bigint(11) DEFAULT '0' COMMENT '这一次真正的月经时间',
  `create_date` bigint(11) DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`menses_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `m_notice_template`
-- ----------------------------
DROP TABLE IF EXISTS `m_notice_template`;
CREATE TABLE `m_notice_template` (
  `notice_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `notice` varchar(512) NOT NULL DEFAULT '',
  `create_date` bigint(11) NOT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `m_wx_template`
-- ----------------------------
DROP TABLE IF EXISTS `m_wx_template`;
CREATE TABLE `m_wx_template` (
  `template_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `wx_template_id` varchar(50) DEFAULT NULL,
  `first` varchar(10) DEFAULT NULL,
  `key_note` varchar(15) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `create_date` int(11) DEFAULT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;